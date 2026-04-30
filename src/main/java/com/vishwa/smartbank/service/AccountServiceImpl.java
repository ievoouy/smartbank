package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.AccountDTO;
import com.vishwa.smartbank.dto.TransactionDTO;
import com.vishwa.smartbank.entity.Account;
import com.vishwa.smartbank.entity.Transaction;
import com.vishwa.smartbank.entity.User;
import com.vishwa.smartbank.exception.InsufficientBalanceException;
import com.vishwa.smartbank.exception.ResourceNotFoundException;
import com.vishwa.smartbank.repository.AccountRepository;
import com.vishwa.smartbank.repository.TransactionRepository;
import com.vishwa.smartbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Account createAccount(AccountDTO accountDTO) {

        User user = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + transactionDTO.getAmount());

        Transaction tx = new Transaction();
        tx.setAmount(transactionDTO.getAmount());
        tx.setType("DEPOSIT");
        tx.setTimestamp(LocalDateTime.now());
        tx.setDestinationAccount(account);

        transactionRepository.save(tx);

        return accountRepository.save(account);
    }

    @Override
    public Account withdraw(TransactionDTO transactionDTO) {

        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));

        if (account.getBalance() < transactionDTO.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - transactionDTO.getAmount());

        Transaction tx = new Transaction();
        tx.setAmount(transactionDTO.getAmount());
        tx.setType("WITHDRAW");
        tx.setTimestamp(LocalDateTime.now());
        tx.setSourceAccount(account);

        transactionRepository.save(tx);

        return accountRepository.save(account);
    }

    @Override
    public Account transfer(Long fromAccountId, Long toAccountId, Double amount) {

        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Sender account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Receiver account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        Transaction tx = new Transaction();
        tx.setAmount(amount);
        tx.setType("TRANSFER");
        tx.setTimestamp(LocalDateTime.now());
        tx.setSourceAccount(fromAccount);
        tx.setDestinationAccount(toAccount);

        transactionRepository.save(tx);

        accountRepository.save(fromAccount);
        return accountRepository.save(toAccount);
    }
}