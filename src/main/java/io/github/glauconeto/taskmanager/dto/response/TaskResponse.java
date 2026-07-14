package io.github.glauconeto.taskmanager.dto.response;

import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
    private UserResponse user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
