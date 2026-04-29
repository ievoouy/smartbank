package com.vishwa.smartbank.controller;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.dto.TransactionDTO;
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

    @PostMapping("/deposit")
    public Account deposit(@RequestBody TransactionDTO transactionDTO) {
        return accountService.deposit(transactionDTO);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestBody TransactionDTO transactionDTO) {
        return accountService.withdraw(transactionDTO);
    }

    @PostMapping("/transfer")
    public Account transfer(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam Double amount) {

        return accountService.transfer(fromAccountId, toAccountId, amount);
    }
}