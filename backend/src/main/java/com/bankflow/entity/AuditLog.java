package com.bankflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String action;
    private String entityType;
    private String entityId;
    @Column(length = 1000)
    private String description;
    private String ipAddress;
    private LocalDateTime createdAt;

    @PrePersist
    public void create() {
        createdAt = LocalDateTime.now();
    }
}
