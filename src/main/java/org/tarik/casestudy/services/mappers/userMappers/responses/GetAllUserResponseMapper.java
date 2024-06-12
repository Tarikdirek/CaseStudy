package org.tarik.casestudy.services.mappers.userMappers.responses;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.services.dtos.user.responses.GetAllUsersResponse;

@Mapper(componentModel = "spring")
public interface GetAllUserResponseMapper {
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(source = "roleName", target = "role.name")
    User getAllUserResponseDtoToUser(GetAllUsersResponse getAllUsersResponse);

    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    GetAllUsersResponse userToGetAllUserResponseDto(User user);
}
