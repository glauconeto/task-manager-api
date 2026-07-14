package io.github.glauconeto.taskmanager.dto.request;

import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status = TaskStatus.PENDING;

    private TaskPriority priority = TaskPriority.MEDIUM;

    private LocalDate dueDate;

    @NotNull(message = "User ID is required")
    private UUID userId;

}
