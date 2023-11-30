package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.dto.UserDto;
import com.kuhar.tasktracker.mappers.UserDtoMapper;
import com.kuhar.tasktracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userMapper;

    public UserDto findByEmail(String email) {
        if (userRepository.existsUserByEmail(email)) {
            return userRepository.findByEmail(email)
                    .map(userMapper::mapEntityToDto)
                    .get();
        }
        throw new UsernameNotFoundException("User with this email:" + email + " does not exist.");
    }
}
