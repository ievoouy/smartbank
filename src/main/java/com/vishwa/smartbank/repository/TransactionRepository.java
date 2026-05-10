package com.vishwa.smartbank.repository;

import com.vishwa.smartbank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySourceAccountUserEmailOrDestinationAccountUserEmail(
            String sourceEmail,
            String destinationEmail
    );
}