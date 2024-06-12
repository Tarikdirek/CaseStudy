package org.tarik.casestudy.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@AllArgsConstructor
@CrossOrigin
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/add")
    public void add(@RequestBody @Valid AddRoleRequest addRoleRequest) {
        roleService.add(addRoleRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateRoleRequest updateRoleRequest) {
        roleService.update(updateRoleRequest);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody @Valid DeleteRoleRequest deleteRoleRequest) {
        roleService.delete(deleteRoleRequest);
    }
    @GetMapping("/getall")
    public List<GetAllRolesResponse> getAll() {
        return roleService.getAll();
    }
    @GetMapping("/getbyid")
    public GetRoleByIdResponse getById(int id) {
        return roleService.getById(id);
    }
    @GetMapping("/getbyname")
    public Role getByName(String name) {
        return roleService.getByName(name);
    }
}
