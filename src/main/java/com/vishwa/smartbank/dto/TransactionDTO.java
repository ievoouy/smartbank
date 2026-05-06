package com.vishwa.smartbank.dto;

import lombok.*;

import jakarta.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @Positive(message = "Amount must be positive")
    private Double amount;
}