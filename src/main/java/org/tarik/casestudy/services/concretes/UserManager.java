package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilities.exceptions.BusinessException;
import org.tarik.casestudy.core.utilities.mappers.ModelMapperService;
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
import org.tarik.casestudy.services.mappers.userMappers.requests.AddUserRequestMapper;
import org.tarik.casestudy.services.mappers.userMappers.requests.UpdateUserRequestMapper;
import org.tarik.casestudy.services.mappers.userMappers.responses.GetAllUserResponseMapper;
import org.tarik.casestudy.services.mappers.userMappers.responses.GetUserByIdResponseMapper;
import org.tarik.casestudy.services.mappers.userMappers.responses.GetUserByNameResponseMapper;

import java.util.List;
@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final ModelMapperService modelMapperService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final AddUserRequestMapper addUserRequestMapper;
    private final GetUserByIdResponseMapper getUserByIdResponseMapper;
    private final GetAllUserResponseMapper getAllUserResponseMapper;
    private final GetUserByNameResponseMapper getUserByNameResponseMapper;
    private final UpdateUserRequestMapper updateUserRequestMapper;

    @Override
    public void add(AddUserRequest addUserRequest) {
        checkIfUserExists(addUserRequest.getUsername());
        User user = addUserRequestMapper.addUserRequestDtoToUser(addUserRequest);
        userRepository.save(user);
    }



    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        User userToUpdate = getUser(updateUserRequest.getId());
        User user =  updateUserRequestMapper.updateUserRequestDtoToUser(updateUserRequest);
        userRepository.save(user);
    }

    @Override
    public void delete(DeleteUserRequest deleteUserRequest) {
        User user = getUser(deleteUserRequest.getId());
        userRepository.delete(user);
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
                .map(getAllUserResponseMapper::userToGetAllUserResponseDto)
                .toList();
        return usersList;
    }


    @Override
    public GetUserByIdResponse getById(int id) {
        var user = getUser(id);
        return getUserByIdResponseMapper.userToGetUserByIdResponseDto(user);
    }

    @Override
    public GetUserByNameResponse getByName(String name) {
        var user = userRepository
                .findByUsername(name)
                .orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
        return getUserByNameResponseMapper.userToGetUserByNameResponseDto(user);
    }


    private void checkIfUserExists(String username) {
        var users = getAll();
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            throw new BusinessException(Messages.USER_ALREADY_EXISTS);
        }
    }

    private User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
    }
    private void checkIsUserAManager(int managerId) {
        var manager = userRepository.findById(managerId).orElseThrow();
        if (!manager.getRole().getName().equals("MANAGER")) {
            throw  new BusinessException(Messages.USER_IS_NOT_A_MANAGER);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new BusinessException(Messages.USER_NOT_FOUND));
        return user;
    }
}
