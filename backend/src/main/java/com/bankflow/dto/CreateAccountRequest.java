package com.bankflow.dto;

import com.bankflow.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
public class CreateAccountRequest {
    @NotNull
    private AccountType accountType;
}
