package io.github.glauconeto.taskmanager.mapper;

import io.github.glauconeto.taskmanager.dto.request.UserRequest;
import io.github.glauconeto.taskmanager.dto.response.UserResponse;
import io.github.glauconeto.taskmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequest request);

    UserResponse toResponse(User entity);

}
