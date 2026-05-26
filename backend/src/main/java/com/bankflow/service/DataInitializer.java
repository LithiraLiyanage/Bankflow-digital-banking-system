package com.bankflow.service;

import com.bankflow.entity.User;
import com.bankflow.enums.*;
import com.bankflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public void run(String... args) {
        create("System Admin", "admin@example.com", "Admin12345", Role.ADMIN);
        create("Demo Customer", "customer@example.com", "Customer12345", Role.CUSTOMER);
    }

    private void create(String n, String e, String p, Role r) {
        if (!users.existsByEmail(e)) users.save(User.builder().fullName(n).email(e).password(encoder.encode(p)).role(r).status(UserStatus.ACTIVE).build());
    }
}
