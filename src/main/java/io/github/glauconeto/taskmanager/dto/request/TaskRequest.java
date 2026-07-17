package io.github.glauconeto.taskmanager.dto.request;

import io.github.glauconeto.taskmanager.entity.TaskPriority;
import io.github.glauconeto.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskStatus status = TaskStatus.PENDING;

    private TaskPriority priority = TaskPriority.MEDIUM;

    private LocalDate dueDate;

}
