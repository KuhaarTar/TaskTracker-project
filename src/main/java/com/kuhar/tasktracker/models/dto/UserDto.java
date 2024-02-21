package com.kuhar.tasktracker.models.dto;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String email) {
}
