package com.vishwa.smartbank.controller;

import com.vishwa.smartbank.entity.Transaction;

import com.vishwa.smartbank.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.security.Principal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions(
            Principal principal
    ) {

        return transactionService
                .getUserTransactions(
                        principal.getName()
                );
    }
}