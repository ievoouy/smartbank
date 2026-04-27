package com.vishwa.smartbank.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;

    private Double balance;

    private String accountType; // SAVINGS / CURRENT

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}