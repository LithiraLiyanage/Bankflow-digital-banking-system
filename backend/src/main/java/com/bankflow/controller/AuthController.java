package com.bankflow.controller;

import com.bankflow.dto.*;
import com.bankflow.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(@Valid @RequestBody RegisterRequest r) {
        return ApiResponse.ok("Registered", auth.register(r));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest r) {
        return ApiResponse.ok("Logged in", auth.login(r));
    }
}
