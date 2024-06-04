package org.tarik.casestudy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.dtos.user.requests.AddUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.DeleteUserRequest;
import org.tarik.casestudy.services.dtos.user.requests.UpdateUserRequest;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@CrossOrigin
public class UsersController {
    private UserService userService;

    @PostMapping("/add")
    public void add(AddUserRequest addUserRequest) {
        userService.add(addUserRequest);
    }
    @PutMapping("/update")
    public void update(UpdateUserRequest updateUserRequest) {
        userService.update(updateUserRequest);
    }
    @DeleteMapping("/delete")
    public void delete(DeleteUserRequest deleteUserRequest) {
        userService.delete(deleteUserRequest);
    }

    @PostMapping("/assignroletouser")
    public void assignRoleToUser(int managerId,int userId, int roleId) {
        userService.assignRoleToUser(managerId,userId,roleId);
    }

    @DeleteMapping("/removerolefromuser")
    public void removeRoleFromUser(int managerId,int assignUser) {
        userService.removeRoleFromUser(managerId, assignUser);
    }
    @GetMapping("/getall")
    public List<GetAllUsersResponse> getAll() {
        return userService.getAll();
    }
    @GetMapping("/getbyid")
    public GetUserByIdResponse getById(int id) {
        return userService.getById(id);
    }
}
