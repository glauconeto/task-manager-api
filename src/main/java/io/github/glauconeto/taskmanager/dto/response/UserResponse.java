package io.github.glauconeto.taskmanager.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private UUID id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

}
