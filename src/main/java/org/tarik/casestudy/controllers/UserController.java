package org.tarik.casestudy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.dtos.user.requests.*;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByNameResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody AddUserRequest addUserRequest) {
        userService.add(addUserRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody UpdateUserRequest updateUserRequest) {
        userService.update(updateUserRequest);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteUserRequest deleteUserRequest) {
        userService.delete(deleteUserRequest);
    }

    @PostMapping("/assignroletouser")
    public void assignRoleToUser(@RequestBody AssignRoleToUserRequest request) {
        userService.assignRoleToUser(request);
    }

    @DeleteMapping("/removerolefromuser")
    public void removeRoleFromUser(@RequestBody RemoveRoleFromUserRequest request) {
        userService.removeRoleFromUser(request);
    }
    @GetMapping("/getall")
    public List<GetAllUsersResponse> getAll() {
        return userService.getAll();
    }
    @GetMapping("/getbyid")
    public GetUserByIdResponse getById(int id) {
        return userService.getById(id);
    }
    @GetMapping("/getbyname")
    public GetUserByNameResponse getByName(String name) {
        return userService.getByName(name);
    }
}
