package com.project.user.domains;

public record LoginResponseDTO(String email, boolean authenticated, String token) {
}
