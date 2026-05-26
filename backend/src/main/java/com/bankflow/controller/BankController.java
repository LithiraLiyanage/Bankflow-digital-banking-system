package com.bankflow.controller;

import com.bankflow.dto.*;
import com.bankflow.dto.TransactionRequests.*;
import com.bankflow.entity.User;
import com.bankflow.repository.UserRepository;
import com.bankflow.service.BankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService bank;
    private final UserRepository users;

    private User user(Authentication a) { return users.findByEmail(a.getName()).orElseThrow(); }

    @PostMapping("/api/accounts")
    public ApiResponse<?> create(Authentication a, @Valid @RequestBody CreateAccountRequest r) {
        return ApiResponse.ok("Account created", bank.createAccount(user(a), r));
    }

    @GetMapping("/api/accounts")
    public ApiResponse<?> accounts(Authentication a) {
        return ApiResponse.ok("Accounts loaded", bank.myAccounts(user(a)));
    }

    @PostMapping("/api/transactions/deposit")
    public ApiResponse<?> deposit(Authentication a, @Valid @RequestBody DepositRequest r) {
        return ApiResponse.ok("Deposit processed", bank.deposit(user(a), r));
    }

    @PostMapping("/api/transactions/withdraw")
    public ApiResponse<?> withdraw(Authentication a, @Valid @RequestBody WithdrawRequest r) {
        return ApiResponse.ok("Withdraw processed", bank.withdraw(user(a), r));
    }

    @PostMapping("/api/transactions/transfer")
    public ApiResponse<?> transfer(Authentication a, @Valid @RequestBody TransferRequest r) {
        return ApiResponse.ok("Transfer processed", bank.transfer(user(a), r));
    }

    @GetMapping("/api/transactions")
    public ApiResponse<?> transactions(Authentication a) {
        return ApiResponse.ok("Transactions loaded", bank.myTransactions(user(a)));
    }
}
