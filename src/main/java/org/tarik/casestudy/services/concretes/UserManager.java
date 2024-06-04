package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.repositories.UserRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;
import org.tarik.casestudy.services.dtos.user.requests.AddUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.DeleteUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.UpdateUserRequest;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;

import java.util.List;
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final ModelMapperService modelMapperService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    @Override
    //halihazırda varsa username hata fırlat
    public void add(AddUserRequest addUserRequest) {
        userRepository.findByUsername(addUserRequest.getUsername());
        User user = modelMapperService.forRequest().map(addUserRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        User userToUpdate = userRepository.findById(updateUserRequest.getId()).orElseThrow();
        User user =  modelMapperService.forRequest().map(updateUserRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public void delete(DeleteUserRequest deleteUserRequest) {
        User user = userRepository.findById(deleteUserRequest.getId()).orElseThrow();
        userRepository.delete(user);
        System.out.println("User deletion is successful");
    }

    @Override
    //parametreleri bir sınıf içerisinde topla
    public void assignRoleToUser(int managerId,int userId, int roleId) {
        checkIsUserAManager(managerId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        GetRoleByIdResponse role = roleService.getById(roleId);
        Role mappedRole = modelMapperService.forRequest().map(role, Role.class);
        user.setRole(mappedRole);
        userRepository.save(user);

    }

    @Override
    //parametreleri bir sınıf içerisinde topla
    public void removeRoleFromUser(int managerId,int assignUser) {
        checkIsUserAManager(managerId);
        User user = userRepository.findById(assignUser).orElseThrow();
        user.setRole(null);
        userRepository.save(user);
    }

    @Override
    public List<GetAllUsersResponse> getAll() {
        var users = userRepository.findAll();
        var usersList = users
                .stream()
                .map(user -> modelMapperService
                        .forResponse()
                        .map(user, GetAllUsersResponse.class))
                .toList();
        return usersList;
    }


    @Override
    public GetUserByIdResponse getById(int id) {
        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapperService
                .forResponse()
                .map(user, GetUserByIdResponse.class);
    }
    private void checkIsUserAManager(int managerId) {
        var manager = userRepository.findById(managerId).orElseThrow();
        if (!manager.getRole().getName().equals("manager")) {
            throw  new RuntimeException("User is not a manager");
        }
    }
}
