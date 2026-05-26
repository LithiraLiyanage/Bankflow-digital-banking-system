package com.bankflow.dto;

import lombok.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String token;
}
