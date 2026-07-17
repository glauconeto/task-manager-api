package io.github.glauconeto.taskmanager.service;

import io.github.glauconeto.taskmanager.dto.request.TaskRequest;
import io.github.glauconeto.taskmanager.dto.response.TaskResponse;
import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    TaskResponse create(TaskRequest request, String userEmail);

    TaskResponse findById(UUID id, String userEmail);

    List<TaskResponse> findAll(String userEmail);

    List<TaskResponse> findByStatus(String userEmail, TaskStatus status);

    List<TaskResponse> findByPriority(String userEmail, TaskPriority priority);

    TaskResponse update(UUID id, TaskRequest request, String userEmail);

    void delete(UUID id, String userEmail);

}
