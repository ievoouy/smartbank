package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.entity.Account;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
}