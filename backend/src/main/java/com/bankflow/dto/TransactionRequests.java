package com.bankflow.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

public class TransactionRequests {
    @Getter @Setter
    public static class DepositRequest {
        @NotNull public Long accountId;
        @NotNull @DecimalMin("0.01") public BigDecimal amount;
        public String description;
    }

    @Getter @Setter
    public static class WithdrawRequest {
        @NotNull public Long accountId;
        @NotNull @DecimalMin("0.01") public BigDecimal amount;
        public String description;
    }

    @Getter @Setter
    public static class TransferRequest {
        @NotNull public Long sourceAccountId;
        @NotBlank public String destinationAccountNumber;
        @NotNull @DecimalMin("0.01") public BigDecimal amount;
        public String description;
    }
}
