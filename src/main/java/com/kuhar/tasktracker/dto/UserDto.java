package com.kuhar.tasktracker.dto;

import lombok.Builder;

@Builder
public record UserDto(String name,
                      String email) {
}
