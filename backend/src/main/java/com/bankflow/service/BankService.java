package com.bankflow.service;

import com.bankflow.dto.*;
import com.bankflow.dto.TransactionRequests.*;
import com.bankflow.entity.*;
import com.bankflow.enums.*;
import com.bankflow.exception.BadRequestException;
import com.bankflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankAccountRepository accounts;
    private final TransactionRepository transactions;

    public BankAccount createAccount(User user, CreateAccountRequest r) {
        return accounts.save(BankAccount.builder()
                .accountNumber("BF" + System.currentTimeMillis())
                .accountType(r.getAccountType())
                .balance(BigDecimal.ZERO)
                .status(AccountStatus.ACTIVE)
                .user(user).build());
    }

    public List<BankAccount> myAccounts(User user) { return accounts.findByUser(user); }

    @Transactional
    public Transaction deposit(User user, DepositRequest r) {
        BankAccount a = own(user, r.accountId);
        active(a);
        a.setBalance(a.getBalance().add(r.amount));
        return tx(user, TransactionType.DEPOSIT, fraud(r.amount), r.amount, null, a, r.description, null);
    }

    @Transactional
    public Transaction withdraw(User user, WithdrawRequest r) {
        BankAccount a = own(user, r.accountId);
        active(a);
        if (a.getBalance().compareTo(r.amount) < 0) throw new BadRequestException("Insufficient balance");
        a.setBalance(a.getBalance().subtract(r.amount));
        String f = fraud(r.amount);
        if (f == null && a.getBalance().add(r.amount).compareTo(BigDecimal.ZERO) > 0 && r.amount.divide(a.getBalance().add(r.amount), 2, java.math.RoundingMode.HALF_UP).compareTo(new BigDecimal("0.80")) > 0) f = "Withdrawal greater than 80% of balance";
        return tx(user, TransactionType.WITHDRAW, f, r.amount, a, null, r.description, null);
    }

    @Transactional
    public Transaction transfer(User user, TransferRequest r) {
        BankAccount s = own(user, r.sourceAccountId);
        BankAccount d = accounts.findByAccountNumber(r.destinationAccountNumber).orElseThrow(() -> new BadRequestException("Destination not found"));
        active(s); active(d);
        if (s.getAccountNumber().equals(d.getAccountNumber())) throw new BadRequestException("Source and destination cannot be same");
        if (s.getBalance().compareTo(r.amount) < 0) throw new BadRequestException("Insufficient balance");
        s.setBalance(s.getBalance().subtract(r.amount));
        d.setBalance(d.getBalance().add(r.amount));
        String f = fraud(r.amount);
        return tx(user, TransactionType.TRANSFER, f, r.amount, s, d, r.description, null);
    }

    public List<Transaction> myTransactions(User user) { return transactions.findByCreatedByOrderByCreatedAtDesc(user); }

    private BankAccount own(User u, Long id) {
        BankAccount a = accounts.findById(id).orElseThrow(() -> new BadRequestException("Account not found"));
        if (!a.getUser().getId().equals(u.getId())) throw new BadRequestException("Access denied");
        return a;
    }

    private void active(BankAccount a) {
        if (a.getStatus() != AccountStatus.ACTIVE) throw new BadRequestException("Account not active");
    }

    private String fraud(BigDecimal amount) {
        return amount.compareTo(new BigDecimal("500000")) > 0 ? "Amount greater than 500,000" : null;
    }

    private Transaction tx(User user, TransactionType type, String fraud, BigDecimal amount, BankAccount source, BankAccount dest, String desc, String fail) {
        return transactions.save(Transaction.builder()
                .referenceNumber("TXN-" + UUID.randomUUID())
                .type(type)
                .status(fraud == null ? TransactionStatus.SUCCESS : TransactionStatus.FLAGGED)
                .amount(amount).sourceAccount(source).destinationAccount(dest)
                .description(desc).failureReason(fail).fraudReason(fraud).createdBy(user).build());
    }
}
