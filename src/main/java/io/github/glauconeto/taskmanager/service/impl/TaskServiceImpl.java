package io.github.glauconeto.taskmanager.service.impl;

import io.github.glauconeto.taskmanager.dto.request.TaskRequest;
import io.github.glauconeto.taskmanager.dto.response.TaskResponse;
import io.github.glauconeto.taskmanager.entity.Task;
import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import io.github.glauconeto.taskmanager.entity.User;
import io.github.glauconeto.taskmanager.mapper.TaskMapper;
import io.github.glauconeto.taskmanager.repository.TaskRepository;
import io.github.glauconeto.taskmanager.repository.UserRepository;
import io.github.glauconeto.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse create(TaskRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Task task = taskMapper.toEntity(request);
        task.setUser(user);

        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
    }

    @Override
    public TaskResponse findById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return taskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponse> findAll() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findByUserId(UUID userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findByUserIdAndStatus(UUID userId, TaskStatus status) {
        return taskRepository.findByUserIdAndStatus(userId, status).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findByUserIdAndPriority(UUID userId, TaskPriority priority) {
        return taskRepository.findByUserIdAndPriority(userId, priority).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setUser(user);

        Task updated = taskRepository.save(task);
        return taskMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepository.deleteById(id);
    }

}
