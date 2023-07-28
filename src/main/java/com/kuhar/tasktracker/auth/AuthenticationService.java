package com.kuhar.tasktracker.auth;

import com.kuhar.tasktracker.requests.AuthenticationRequest;
import com.kuhar.tasktracker.requests.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public Object register(RegisterRequest request) {
        return null;
    }

    public Object authenticate(AuthenticationRequest request) {
        return null;
    }

    public Object logout() {
        return null;
    }
}
