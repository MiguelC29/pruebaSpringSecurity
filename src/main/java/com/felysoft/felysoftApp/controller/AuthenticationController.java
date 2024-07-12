package com.felysoft.felysoftApp.controller;

import com.felysoft.felysoftApp.dto.AuthenticationRequest;
import com.felysoft.felysoftApp.dto.AuthenticationResponse;
import com.felysoft.felysoftApp.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    /*
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }*/

    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authenticationService.login(authRequest));
    }

    @PreAuthorize("permitAll")
    @GetMapping("/public-access")
    public String publicAccessEndPoint() {
        return "este endpoint es público";
    }
}
