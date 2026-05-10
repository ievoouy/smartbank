package com.vishwa.smartbank.service;

import com.vishwa.smartbank.entity.Transaction;

import com.vishwa.smartbank.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl
        implements TransactionService {

    @Autowired
    private TransactionRepository
            transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {

        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getUserTransactions(
            String email
    ) {

        return transactionRepository
                .findBySourceAccountUserEmailOrDestinationAccountUserEmail(
                        email,
                        email
                );
    }
}