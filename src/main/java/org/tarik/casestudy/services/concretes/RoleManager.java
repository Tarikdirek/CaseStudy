package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilities.exceptions.BusinessException;
import org.tarik.casestudy.core.utilities.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.repositories.RoleRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.constants.Messages;
import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;
import org.tarik.casestudy.services.mappers.roleMappers.requests.AddRoleRequestMapper;
import org.tarik.casestudy.services.mappers.roleMappers.requests.DeleteRoleRequestMapper;
import org.tarik.casestudy.services.mappers.roleMappers.requests.UpdateRoleRequestMapper;
import org.tarik.casestudy.services.mappers.roleMappers.responses.GetAllRoleResponseMapper;
import org.tarik.casestudy.services.mappers.roleMappers.responses.GetRoleByIdResponseMapper;

import java.util.List;
@AllArgsConstructor
@Service
public class RoleManager implements RoleService {
   // private final ModelMapperService modelMapperService;
    private final RoleRepository roleRepository;
    private final GetAllRoleResponseMapper getAllRoleResponseMapper;
    private final GetRoleByIdResponseMapper getRoleByIdResponseMapper;
    private final AddRoleRequestMapper addRoleRequestMapper;
    private final UpdateRoleRequestMapper updateRoleRequestMapper;

    @Override
    public void add(AddRoleRequest addRoleRequest) {
        checkIfRoleExists(addRoleRequest.getName());
        Role role = addRoleRequestMapper.addRoleRequestDtoToRole(addRoleRequest);
        roleRepository.save(role);

    }



    @Override
    public void update(UpdateRoleRequest updateRoleRequest) {
             roleRepository.findById(updateRoleRequest.getId())
                .orElseThrow(() -> new BusinessException(Messages.ROLE_NOT_FOUND));
        Role roleToUpdate = updateRoleRequestMapper.updateRoleRequestDtoToUpdateRoleRequest(updateRoleRequest);
        roleRepository.save(roleToUpdate);
    }

    @Override
    public void delete(DeleteRoleRequest deleteRoleRequest) {
        Role roleToDelete = roleRepository.findById(deleteRoleRequest.getId())
                .orElseThrow(() -> new BusinessException(Messages.ROLE_NOT_FOUND));
         roleRepository.delete(roleToDelete);
    }



    @Override
    public List<GetAllRolesResponse> getAll() {
        var roles = roleRepository.findAll();

       return roles.stream().map((getAllRoleResponseMapper::roleToGetAllRoleResponseDto))
                .toList();

//        return  roles.stream()
//                .map(role -> modelMapperService.forResponse()
//                        .map(role, GetAllRolesResponse.class))
//                .toList();

    }

    @Override
    public GetRoleByIdResponse getById(int id) {
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(Messages.ROLE_NOT_FOUND));
//        return  modelMapperService.forResponse()
//                .map(role, GetRoleByIdResponse.class);

        return getRoleByIdResponseMapper.roleToGetRoleByIdResponseDto(role);

    }

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new BusinessException(Messages.ROLE_NOT_FOUND));
    }

    private void checkIfRoleExists(String roleName) {
        var roles = getAll();
        if (roles.stream().anyMatch(role -> role.getName().equals(roleName))) {
            throw new BusinessException(Messages.ROLE_ALREADY_EXISTS);
        }
    }

}
