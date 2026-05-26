package com.bankflow.controller;

import com.bankflow.dto.ApiResponse;
import com.bankflow.enums.TransactionStatus;
import com.bankflow.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserRepository users;
    private final BankAccountRepository accounts;
    private final TransactionRepository transactions;
    private final AuditLogRepository auditLogs;

    @GetMapping("/dashboard")
    public ApiResponse<?> dashboard() {
        Map<String,Object> d = new LinkedHashMap<>();
        d.put("totalUsers", users.count());
        d.put("totalAccounts", accounts.count());
        d.put("totalTransactions", transactions.count());
        d.put("flaggedTransactions", transactions.findByStatus(TransactionStatus.FLAGGED).size());
        return ApiResponse.ok("Dashboard loaded", d);
    }

    @GetMapping("/users") public ApiResponse<?> users() { return ApiResponse.ok("Users", users.findAll()); }
    @GetMapping("/accounts") public ApiResponse<?> accounts() { return ApiResponse.ok("Accounts", accounts.findAll()); }
    @GetMapping("/transactions") public ApiResponse<?> tx() { return ApiResponse.ok("Transactions", transactions.findAll()); }
    @GetMapping("/transactions/flagged") public ApiResponse<?> flagged() { return ApiResponse.ok("Flagged", transactions.findByStatus(TransactionStatus.FLAGGED)); }
    @GetMapping("/audit-logs") public ApiResponse<?> audit() { return ApiResponse.ok("Audit", auditLogs.findAll()); }
}
