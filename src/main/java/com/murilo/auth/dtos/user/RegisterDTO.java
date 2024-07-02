package com.murilo.auth.dtos.user;

import com.murilo.auth.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role ) {
}
