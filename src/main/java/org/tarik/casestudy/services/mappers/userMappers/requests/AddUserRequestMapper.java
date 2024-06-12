package org.tarik.casestudy.services.mappers.userMappers.requests;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.services.dtos.user.requests.AddUserRequest;


@Mapper(componentModel = "spring")
public interface AddUserRequestMapper {



    @Mapping(source = "roleId", target = "role.id")
    public abstract User addUserRequestDtoToUser(AddUserRequest addUserRequest);

    @Mapping(source = "role.id", target = "roleId")
    public abstract AddUserRequest userToAddUserRequestDto(User user);


}
