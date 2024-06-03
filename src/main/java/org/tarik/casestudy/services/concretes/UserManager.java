package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.repositories.UserRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;
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
    public void add(AddUserRequest addUserRequest) {

    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {

    }

    @Override
    public void delete(DeleteUserRequest deleteUserRequest) {

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
}
