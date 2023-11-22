package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.dto.UserDTO;
import com.kuhar.tasktracker.models.User;
import com.kuhar.tasktracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userMapper;

    public void save(User user) {
        userRepository.save(user);
    }

    public UserDTO findByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper)
                .orElseThrow(() -> new RuntimeException("User not found exception"));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper).collect(Collectors.toList());
    }

    public UserDTO findByEmail(String email) {
        if(userRepository.existsUserByEmail(email)){
            return userRepository.findByEmail(email)
                    .map(userMapper)
                    .get();
        }
        throw new UsernameNotFoundException("User with this email:" + email + " does not exist.");
    }
}
