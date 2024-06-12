package org.tarik.casestudy.services.mappers.roleMappers.requests;

import org.mapstruct.Mapper;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;

@Mapper(componentModel = "spring")
public interface AddRoleRequestMapper {
    Role addRoleRequestDtoToRole(AddRoleRequest addRoleRequest);

    AddRoleRequest roleToAddRoleRequestDto(Role role);
}
