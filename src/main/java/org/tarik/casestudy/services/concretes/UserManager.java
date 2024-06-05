package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.repositories.UserRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.constants.Messages;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;
import org.tarik.casestudy.services.dtos.user.requests.*;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByNameResponse;

import java.util.List;
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final ModelMapperService modelMapperService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    @Override
    public void add(AddUserRequest addUserRequest) {
        checkIfUserExists(addUserRequest.getUsername());
        User user = modelMapperService.forRequest().map(addUserRequest, User.class);
        userRepository.save(user);
    }



    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        User userToUpdate = getUser(updateUserRequest.getId());
        User user =  modelMapperService.forRequest().map(updateUserRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public void delete(DeleteUserRequest deleteUserRequest) {
        User user = getUser(deleteUserRequest.getId());
        userRepository.delete(user);
        System.out.println("User deletion is successful");
    }



    @Override
    public void assignRoleToUser(AssignRoleToUserRequest request) {
        checkIsUserAManager(request.getManagerId());
        User user = getUser(request.getUserId());
        GetRoleByIdResponse role = roleService.getById(request.getRoleId());
        Role mappedRole = modelMapperService.forRequest().map(role, Role.class);
        user.setRole(mappedRole);
        userRepository.save(user);

    }

    @Override
    public void removeRoleFromUser(RemoveRoleFromUserRequest request) {
        checkIsUserAManager(request.getManagerId());
        User user = getUser(request.getUserId());
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
        var user = getUser(id);
        return modelMapperService
                .forResponse()
                .map(user, GetUserByIdResponse.class);
    }

    @Override
    public GetUserByNameResponse getByName(String name) {
        var user = userRepository
                .findByUsername(name)
                .orElseThrow(() -> new RuntimeException(Messages.USER_NOT_FOUND));
        return modelMapperService
                .forResponse()
                .map(user, GetUserByNameResponse.class);
    }


    private void checkIfUserExists(String username) {
        var users = getAll();
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            throw new RuntimeException(Messages.USER_ALREADY_EXISTS);
        }
    }

    private User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(Messages.USER_NOT_FOUND));
    }
    private void checkIsUserAManager(int managerId) {
        var manager = userRepository.findById(managerId).orElseThrow();
        if (!manager.getRole().getName().equals("manager")) {
            throw  new RuntimeException(Messages.USER_IS_NOT_A_MANAGER);
        }
    }
}
