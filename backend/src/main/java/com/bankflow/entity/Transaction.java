package com.bankflow.entity;

import com.bankflow.enums.*;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String referenceNumber;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount sourceAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount destinationAccount;
    private String description;
    private String failureReason;
    private String fraudReason;
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;
    private LocalDateTime createdAt;

    @PrePersist
    public void create() {
        createdAt = LocalDateTime.now();
    }
}
