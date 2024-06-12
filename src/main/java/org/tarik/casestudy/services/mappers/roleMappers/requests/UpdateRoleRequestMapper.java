package org.tarik.casestudy.services.mappers.roleMappers.requests;

import org.mapstruct.Mapper;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;

@Mapper(componentModel = "spring")
public interface UpdateRoleRequestMapper {
    Role updateRoleRequestDtoToUpdateRoleRequest(UpdateRoleRequest updateRoleRequest);
    UpdateRoleRequest updateRoleRequestDtoToUpdateRoleRequestDto(Role role);
}
