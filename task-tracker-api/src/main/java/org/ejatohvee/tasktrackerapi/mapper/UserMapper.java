package org.ejatohvee.tasktrackerapi.mapper;

import org.ejatohvee.tasktrackerapi.dtos.responses.UserResponse;
import org.ejatohvee.tasktrackerapi.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
