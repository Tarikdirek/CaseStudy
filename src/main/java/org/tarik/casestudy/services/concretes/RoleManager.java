package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.repositories.RoleRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.constants.Messages;
import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;

import java.util.List;
@AllArgsConstructor
@Service
public class RoleManager implements RoleService {
    private final ModelMapperService modelMapperService;
    private final RoleRepository roleRepository;

    @Override
    public void add(AddRoleRequest addRoleRequest) {
        checkIfRoleExists(addRoleRequest.getName());
        Role role = modelMapperService.forRequest().map(addRoleRequest, Role.class);
        roleRepository.save(role);

    }



    @Override
    public void update(UpdateRoleRequest updateRoleRequest) {
             roleRepository.findById(updateRoleRequest.getId())
                .orElseThrow(() -> new RuntimeException(Messages.ROLE_NOT_FOUND));
        Role roleToUpdate = this.modelMapperService.forRequest().map(updateRoleRequest, Role.class);
        roleRepository.save(roleToUpdate);
    }

    @Override
    public void delete(DeleteRoleRequest deleteRoleRequest) {
        Role roleToDelete = roleRepository.findById(deleteRoleRequest.getId())
                .orElseThrow(() -> new RuntimeException(Messages.ROLE_NOT_FOUND));
         roleRepository.delete(roleToDelete);
    }



    @Override
    public List<GetAllRolesResponse> getAll() {
        var roles = roleRepository.findAll();
        return  roles.stream()
                .map(role -> modelMapperService.forResponse()
                        .map(role, GetAllRolesResponse.class))
                .toList();

    }

    @Override
    public GetRoleByIdResponse getById(int id) {
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Messages.ROLE_NOT_FOUND));
        return  modelMapperService.forResponse()
                .map(role, GetRoleByIdResponse.class);

    }

    private void checkIfRoleExists(String roleName) {
        var roles = getAll();
        if (roles.stream().anyMatch(role -> role.getName().equals(roleName))) {
            throw new RuntimeException(Messages.ROLE_ALREADY_EXISTS);
        }
    }

}
