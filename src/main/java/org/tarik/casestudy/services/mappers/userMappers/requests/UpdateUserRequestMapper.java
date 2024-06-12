package org.tarik.casestudy.services.mappers.userMappers.requests;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.services.dtos.user.requests.UpdateUserRequest;

@Mapper(componentModel = "spring")
public interface UpdateUserRequestMapper {
    @Mapping(source = "roleId", target = "role.id")
    User updateUserRequestDtoToUser(UpdateUserRequest updateUserRequest);

    @Mapping(source = "role.id", target = "roleId")
    UpdateUserRequest userToUpdateUserRequestDto(User user);
}
