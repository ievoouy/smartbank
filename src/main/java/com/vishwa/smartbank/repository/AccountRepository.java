package com.vishwa.smartbank.repository;

import com.vishwa.smartbank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}