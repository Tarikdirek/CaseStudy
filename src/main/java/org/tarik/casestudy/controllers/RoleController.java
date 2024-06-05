package org.tarik.casestudy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public void add(@RequestBody AddRoleRequest addRoleRequest) {
        roleService.add(addRoleRequest);
    }
    @PutMapping("/update")
    public void update(@RequestBody UpdateRoleRequest updateRoleRequest) {
        roleService.update(updateRoleRequest);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteRoleRequest deleteRoleRequest) {
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
}
