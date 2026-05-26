package com.bankflow.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
public class RegisterRequest {
    @NotBlank @Size(min = 2)
    private String fullName;
    @NotBlank @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must include uppercase, lowercase and number")
    private String password;
}
