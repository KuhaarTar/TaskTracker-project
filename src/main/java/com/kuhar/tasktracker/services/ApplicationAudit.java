package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.models.User;
import com.kuhar.tasktracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationAudit implements AuditorAware<User> {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return userRepository.findByEmail(authentication.getName());
    }

    public User returnCurrentUser() {
        Optional<User> principal = getCurrentAuditor();
        if (principal.isEmpty()) {
            throw new UsernameNotFoundException("Not found such authenticated user");
        }
        return principal.get();
    }
}
