package com.bankflow.service;

import com.bankflow.dto.*;
import com.bankflow.entity.User;
import com.bankflow.enums.*;
import com.bankflow.repository.UserRepository;
import com.bankflow.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager auth;
    private final JwtService jwt;

    public AuthResponse register(RegisterRequest r) {
        Role role = r.getEmail().equalsIgnoreCase("admin@example.com") ? Role.ADMIN : Role.CUSTOMER;
        User u = User.builder().fullName(r.getFullName()).email(r.getEmail()).password(encoder.encode(r.getPassword())).role(role).status(UserStatus.ACTIVE).build();
        users.save(u);
        return AuthResponse.builder().id(u.getId()).fullName(u.getFullName()).email(u.getEmail()).role(u.getRole().name()).token(jwt.generate(u)).build();
    }

    public AuthResponse login(LoginRequest r) {
        auth.authenticate(new UsernamePasswordAuthenticationToken(r.getEmail(), r.getPassword()));
        User u = users.findByEmail(r.getEmail()).orElseThrow();
        return AuthResponse.builder().id(u.getId()).fullName(u.getFullName()).email(u.getEmail()).role(u.getRole().name()).token(jwt.generate(u)).build();
    }
}
