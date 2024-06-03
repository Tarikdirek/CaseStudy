package org.tarik.casestudy.services.abstracts;

import org.tarik.casestudy.services.dtos.user.requests.AddUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.DeleteUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.UpdateUserRequest;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;

import java.util.List;

public interface UserService {
    void add(AddUserRequest addUserRequest);
    void update(UpdateUserRequest updateUserRequest);
    void delete(DeleteUserRequest deleteUserRequest);

    List<GetAllUsersResponse> getAll();
    GetUserByIdResponse getById(int id);
}
