package com.felysoft.felysoftApp.auth;

import com.felysoft.felysoftApp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
