package com.bankflow.repository;

import com.bankflow.entity.*;
import com.bankflow.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.*;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCreatedByOrderByCreatedAtDesc(User user);
    List<Transaction> findByStatus(TransactionStatus status);
    long countBySourceAccountAndTypeAndCreatedAtAfter(BankAccount source, TransactionType type, LocalDateTime after);
}
