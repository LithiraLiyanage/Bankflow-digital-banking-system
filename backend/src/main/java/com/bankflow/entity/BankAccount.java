package com.bankflow.entity;

import com.bankflow.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void create() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (balance == null) balance = BigDecimal.ZERO;
        if (status == null) status = AccountStatus.ACTIVE;
    }
}
