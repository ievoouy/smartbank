package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.dto.TransactionDTO;
import com.vishwa.smartbank.entity.Account;
import com.vishwa.smartbank.entity.User;
import com.vishwa.smartbank.repository.AccountRepository;
import com.vishwa.smartbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Account createAccount(AccountDTO accountDTO) {

        User user = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setUser(user);
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getInitialBalance());

        // Generate random account number
        String accNumber = "SB" + (100000 + new Random().nextInt(900000));
        account.setAccountNumber(accNumber);

        return accountRepository.save(account);
    }

    @Override
    public Account deposit(TransactionDTO transactionDTO) {

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + transactionDTO.getAmount());

        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(TransactionDTO transactionDTO) {

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - transactionDTO.getAmount());

        return accountRepository.save(account);
    }
}