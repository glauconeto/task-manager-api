package io.github.glauconeto.taskmanager.mapper;

import io.github.glauconeto.taskmanager.dto.request.TaskRequest;
import io.github.glauconeto.taskmanager.dto.response.TaskResponse;
import io.github.glauconeto.taskmanager.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TaskMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(TaskRequest request);

    TaskResponse toResponse(Task entity);

}
