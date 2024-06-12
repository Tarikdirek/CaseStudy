package org.tarik.casestudy.services.mappers.roleMappers.responses;

import org.mapstruct.Mapper;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;

@Mapper(componentModel = "spring")
public interface GetAllRoleResponseMapper {

    Role getAllRoleResponseDtoToRole(GetAllRolesResponse getAllRolesResponse);

    GetAllRolesResponse roleToGetAllRoleResponseDto(Role role);


}
