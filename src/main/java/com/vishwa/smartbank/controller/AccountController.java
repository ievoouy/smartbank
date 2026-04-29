package com.vishwa.smartbank.controller;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.entity.Account;
import com.vishwa.smartbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }
}