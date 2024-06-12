package org.tarik.casestudy.services.mappers.roleMappers.requests;

import org.mapstruct.Mapper;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;

@Mapper(componentModel = "spring")
public interface DeleteRoleRequestMapper {
    Role roleDtoToDeleteRoleRequest(DeleteRoleRequest deleteRoleRequest);

    DeleteRoleRequest deleteRoleRequestDtoToDeleteRoleRequestDto(Role role);
}
