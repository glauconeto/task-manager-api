package io.github.glauconeto.taskmanager.service;

import io.github.glauconeto.taskmanager.dto.request.TaskRequest;
import io.github.glauconeto.taskmanager.dto.response.TaskResponse;
import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse create(TaskRequest request);

    TaskResponse findById(UUID id);

    List<TaskResponse> findAll();

    List<TaskResponse> findByUserId(UUID userId);

    List<TaskResponse> findByUserIdAndStatus(UUID userId, TaskStatus status);

    List<TaskResponse> findByUserIdAndPriority(UUID userId, TaskPriority priority);

    TaskResponse update(UUID id, TaskRequest request);

    void delete(UUID id);

}
