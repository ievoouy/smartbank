package com.vishwa.smartbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "handler"
})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private String type; // DEPOSIT / WITHDRAW / TRANSFER

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    @JsonIgnore
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    @JsonIgnore
    private Account destinationAccount;
}