package com.bankflow.repository;

import com.bankflow.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByUser(User user);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
