package io.github.glauconeto.taskmanager.service.impl;

import io.github.glauconeto.taskmanager.dto.request.TaskRequest;
import io.github.glauconeto.taskmanager.dto.response.TaskResponse;
import io.github.glauconeto.taskmanager.entity.Task;
import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import io.github.glauconeto.taskmanager.entity.User;
import io.github.glauconeto.taskmanager.exception.ResourceNotFoundException;
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
    public TaskResponse create(TaskRequest request, String userEmail) {
        User user = findUserByEmail(userEmail);

        Task task = taskMapper.toEntity(request);
        task.setUser(user);

        Task saved = taskRepository.save(task);
        return taskMapper.toResponse(saved);
    }

    @Override
    public TaskResponse findById(UUID id, String userEmail) {
        Task task = findOwnedTask(id, userEmail);
        return taskMapper.toResponse(task);
    }

    @Override
    public List<TaskResponse> findAll(String userEmail) {
        User user = findUserByEmail(userEmail);
        return taskRepository.findByUserId(user.getId()).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findByStatus(String userEmail, TaskStatus status) {
        User user = findUserByEmail(userEmail);
        return taskRepository.findByUserIdAndStatus(user.getId(), status).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public List<TaskResponse> findByPriority(String userEmail, TaskPriority priority) {
        User user = findUserByEmail(userEmail);
        return taskRepository.findByUserIdAndPriority(user.getId(), priority).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse update(UUID id, TaskRequest request, String userEmail) {
        Task task = findOwnedTask(id, userEmail);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());

        Task updated = taskRepository.save(task);
        return taskMapper.toResponse(updated);
    }

    @Override
    public void delete(UUID id, String userEmail) {
        findOwnedTask(id, userEmail);
        taskRepository.deleteById(id);
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Task findOwnedTask(UUID id, String userEmail) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new ResourceNotFoundException("Task not found");
        }

        return task;
    }

}
