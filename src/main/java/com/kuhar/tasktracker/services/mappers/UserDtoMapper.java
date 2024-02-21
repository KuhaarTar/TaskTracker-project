package com.kuhar.tasktracker.services.mappers;

import com.kuhar.tasktracker.annotations.Mapper;
import com.kuhar.tasktracker.models.dto.UserDto;
import com.kuhar.tasktracker.models.User;

@Mapper
public class UserDtoMapper implements BaseEntityMapper<User, UserDto> {
    @Override
    public UserDto mapEntityToDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
