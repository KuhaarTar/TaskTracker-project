package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.dto.UserDTO;
import com.kuhar.tasktracker.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getName(),
                user.getEmail(),
                user.getGitHubRef(),
                user.getRole());
    }
}
