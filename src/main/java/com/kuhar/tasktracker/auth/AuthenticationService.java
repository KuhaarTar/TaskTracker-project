package com.kuhar.tasktracker.auth;

import com.kuhar.tasktracker.enums.Role;
import com.kuhar.tasktracker.enums.TokenType;
import com.kuhar.tasktracker.exceptions.PasswordsDoesNotMatchException;
import com.kuhar.tasktracker.exceptions.TokenException;
import com.kuhar.tasktracker.models.Token;
import com.kuhar.tasktracker.models.User;
import com.kuhar.tasktracker.repositories.TokenRepository;
import com.kuhar.tasktracker.repositories.UserRepository;
import com.kuhar.tasktracker.requests.AuthenticationRequest;
import com.kuhar.tasktracker.requests.RegisterRequest;
import com.kuhar.tasktracker.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordsDoesNotMatchException("Passwords don't match. Try again.");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String accessToken = createAccessToken(user);
        String refreshToken = createRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticationManager.authenticate(authenticationToken);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with this email:" + request.getEmail() + " does not exist."));
        String accessToken = createAccessToken(user);
        String refreshToken = createRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String createAccessToken(User user) {
        String access = jwtService.createToken(user);
        Token token = Token.builder()
                .token(access)
                .user(user)
                .type(TokenType.ACCESS)
                .build();
        tokenRepository.save(token);
        return access;
    }

    private String createRefreshToken(User user) {
        String refresh = jwtService.createRefreshToken(user);
        Token token = Token.builder()
                .token(refresh)
                .user(user)
                .type(TokenType.REFRESH)
                .build();
        tokenRepository.save(token);
        return refresh;
    }

    public AuthenticationResponse refreshToken(
            HttpServletRequest request) {
        User user = extractUserFromRequest(request);
        deleteOldTokens(request);
        String accessToken = createAccessToken(user);
        String refreshToken = createRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void deleteOldTokens(HttpServletRequest request) {
        User user = extractUserFromRequest(request);
        List<Token> tokens = tokenRepository.findTokensByUser(user);
        List<Long> ids = tokens.stream().map(Token::getId).collect(Collectors.toList());
        tokenRepository.deleteAllById(ids);
    }

    private User extractUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        if (!(tokenRepository.findByToken(token).get().getType().equals(TokenType.REFRESH))) {
            throw new TokenException("That must be refresh token , not access.");
        }
        String userEmail = jwtService.extractEmail(token);
        return userRepository
                .findByEmail(userEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with this email:" + userEmail + " does not exist.")
                );
    }
}
