package com.project.user.service.impl;

import com.project.user.domains.UserRequestDTO;
import com.project.user.exception.UserAlreadyExistsException;
import com.project.user.model.PhoneModel;
import com.project.user.model.UserModel;
import com.project.user.repository.UserRepository;
import com.project.user.service.JwtService;
import com.project.user.service.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Value("${validation.password.pattern}")
    private String passwordRegex;

    private final ModelMapper modelMapper;

    @Override
    public Optional<UserModel> findById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @SneakyThrows
    @Override
    public UserModel save(UserRequestDTO userRequestDTO) {
        final Optional<UserModel> userOptional = userRepository.findByEmail(userRequestDTO.getEmail());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User email already exists.");
        } else {

            List<PhoneModel> phonesList = userRequestDTO.getPhones()
                    .stream()
                    .map(phones -> modelMapper.map(phones, PhoneModel.class))
                    .collect(Collectors.toList());

            UserModel newUser = UserModel.builder()
                    .name(userRequestDTO.getName())
                    .email(userRequestDTO.getEmail())
                    .password(passwordEncoder.encode(userRequestDTO.getPassword()))
                    .lastLogin(new Date())
                    .modified(new Date())
                    .active(Boolean.TRUE)
                    .created(new Date())
                    .phones(phonesList).build();

            for (PhoneModel phone: newUser.getPhones()) {
                phone.setUser(newUser);
            }
            var token = jwtService.generateToken(newUser);
            newUser.setToken(token);
            return userRepository.save(newUser);
        }
    }

    @Override
    public boolean isValidPassword(String password) {
        return password.matches(passwordRegex);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel update(UserModel user) {
        return userRepository.save(user);
    }

}
