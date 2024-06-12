package org.tarik.casestudy.services.mappers.roleMappers.responses;

import org.mapstruct.Mapper;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;

@Mapper(componentModel = "spring")
public interface GetRoleByIdResponseMapper {
    Role getRoleByIdResponseDtoToRole(GetRoleByIdResponse getRoleByIdResponse);

    GetRoleByIdResponse roleToGetRoleByIdResponseDto(Role role);
}
