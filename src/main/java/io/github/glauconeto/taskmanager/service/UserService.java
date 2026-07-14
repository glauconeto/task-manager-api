package io.github.glauconeto.taskmanager.service;

import io.github.glauconeto.taskmanager.dto.request.UserRequest;
import io.github.glauconeto.taskmanager.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);

    UserResponse findById(UUID id);

    List<UserResponse> findAll();

    UserResponse update(UUID id, UserRequest request);

    void delete(UUID id);

}
