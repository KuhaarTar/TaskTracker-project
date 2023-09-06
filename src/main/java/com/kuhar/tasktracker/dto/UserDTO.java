package com.kuhar.tasktracker.dto;

import com.kuhar.tasktracker.enums.Role;

public record UserDTO(
        String name,
        String email,
        String gitHubRef,
        Role role) {
}
