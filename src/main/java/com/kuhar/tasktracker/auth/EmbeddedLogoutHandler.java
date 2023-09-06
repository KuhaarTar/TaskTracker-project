package com.kuhar.tasktracker.auth;

import com.kuhar.tasktracker.exceptions.LogoutException;
import com.kuhar.tasktracker.models.Token;
import com.kuhar.tasktracker.models.User;
import com.kuhar.tasktracker.repositories.TokenRepository;
import com.kuhar.tasktracker.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmbeddedLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) throws LogoutException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LogoutException("You are not logged in , can not logout.");
        }
        jwt = authHeader.substring(7);
        Optional<User> user = userRepository.findByEmail(jwtService.extractEmail(jwt));
        if (user.isPresent()) {
            List<Token> tokens = tokenRepository.findTokensByUser(user.get());
            List<Long> ids = tokens.stream().map(Token::getId).collect(Collectors.toList());
            tokenRepository.deleteAllById(ids);
        } else {
            throw new LogoutException("Not such user to logout.");
        }
    }
}
