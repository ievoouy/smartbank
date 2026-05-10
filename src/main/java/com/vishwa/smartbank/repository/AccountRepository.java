package com.vishwa.smartbank.repository;

import com.vishwa.smartbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserEmail(String email);
}