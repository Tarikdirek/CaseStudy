package org.tarik.casestudy.services.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.tarik.casestudy.services.dtos.user.requests.*;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByNameResponse;

import java.util.List;

public interface UserService extends UserDetailsService {
    void add(AddUserRequest addUserRequest);
    void update(UpdateUserRequest updateUserRequest);
    void delete(DeleteUserRequest deleteUserRequest);
    void assignRoleToUser(AssignRoleToUserRequest request);
    void removeRoleFromUser(RemoveRoleFromUserRequest request);


    List<GetAllUsersResponse> getAll();
    GetUserByIdResponse getById(int id);
    GetUserByNameResponse getByName(String name);
}
