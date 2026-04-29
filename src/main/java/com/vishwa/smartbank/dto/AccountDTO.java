package com.vishwa.smartbank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long userId;
    private String accountType; // SAVINGS / CURRENT
    private Double initialBalance;
}