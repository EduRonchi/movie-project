package com.eduronchi.movieproject.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
