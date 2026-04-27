package com.vishwa.smartbank.repository;

import com.vishwa.smartbank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}