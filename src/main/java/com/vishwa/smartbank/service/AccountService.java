package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.dto.TransactionDTO;
import com.vishwa.smartbank.entity.Account;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
    Account deposit(TransactionDTO transactionDTO);
    Account withdraw(TransactionDTO transactionDTO);
    Account transfer(Long fromAccountId, Long toAccountId, Double amount);
}