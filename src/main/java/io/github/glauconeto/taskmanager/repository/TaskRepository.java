package io.github.glauconeto.taskmanager.repository;

import io.github.glauconeto.taskmanager.entity.Task;
import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByUserId(UUID userId);

    List<Task> findByUserIdAndStatus(UUID userId, TaskStatus status);

    List<Task> findByUserIdAndPriority(UUID userId, TaskPriority priority);

}
