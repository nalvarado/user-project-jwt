package com.project.user.service;

import com.project.user.domains.UserRequestDTO;
import com.project.user.model.UserModel;
import lombok.SneakyThrows;

import java.util.Optional;
import java.util.UUID;
public interface UserServiceInterface{
    Optional<UserModel> findById(UUID uuid);

    @SneakyThrows
    UserModel save(UserRequestDTO userRequestDTO);

    boolean isValidPassword(String password);

    Optional<UserModel> findByEmail(String email);

    public UserModel update(UserModel user);
}
