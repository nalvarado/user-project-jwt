package com.project.user.controller;

import com.project.user.domains.AuthResponseDTO;
import com.project.user.domains.ErrorResponseDTO;
import com.project.user.domains.LoginRequestDTO;
import com.project.user.domains.LoginResponseDTO;
import com.project.user.domains.UserRequestDTO;
import com.project.user.model.UserModel;
import com.project.user.service.UserServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@Tag(name = "Tutorial", description = "Tutorial Desafio Java BCI")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final UserServiceInterface userService;

    private final AuthenticationManager authenticationManager;

    private final ModelMapper modelMapper;

    @Operation(summary = "Registro de usuario",
            responses = {
                    @ApiResponse(responseCode = "400", description = "bad input",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Error de sistema",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "409", description = "Error correo ya registrado",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "201", description = "Usuario registrado con exito"),
            })
    @PostMapping("/register")
    private ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDTO createUserRequestDTO){
        if (!userService.isValidPassword(createUserRequestDTO.getPassword())) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Password does not match with requirements."));
        }

        UserModel savedUser = userService.save(createUserRequestDTO);
        AuthResponseDTO response = modelMapper.map(savedUser, AuthResponseDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Login de usuario",
            responses = {
                    @ApiResponse(responseCode = "400", description = "bad input",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "500", description = "Error de sistema",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "No Autorizado",
                            content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
                    @ApiResponse(responseCode = "200", description = "Acceso Exitoso"),
            })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequest) {

        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        var token = new String();
        if (authenticationResponse.isAuthenticated()) {
            Optional<UserModel> userOptional = userService.findByEmail(loginRequest.getEmail());
            if (userOptional.isPresent()) {
                userOptional.get().setLastLogin(new Date());
                UserModel user = userOptional.get();
                token = user.getToken();
                userService.update(user);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(authenticationResponse.getName(),
                                                              authenticationResponse.isAuthenticated(),token ));
    }

}
