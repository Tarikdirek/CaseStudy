package org.tarik.casestudy.controllers;

import jakarta.validation.Valid;
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
    public void add(@RequestBody @Valid AddUserRequest addUserRequest) {
        userService.add(addUserRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        userService.update(updateUserRequest);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) {
        userService.delete(deleteUserRequest);
    }

    @PostMapping("/assignroletouser")
    public void assignRoleToUser(@RequestBody @Valid AssignRoleToUserRequest request) {
        userService.assignRoleToUser(request);
    }

    @PostMapping("/removerolefromuser")
    public void removeRoleFromUser(@RequestBody @Valid RemoveRoleFromUserRequest request) {
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
