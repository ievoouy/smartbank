package com.vishwa.smartbank.service;

import com.vishwa.smartbank.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();
    List<Transaction> getUserTransactions(
            String email
    );
}