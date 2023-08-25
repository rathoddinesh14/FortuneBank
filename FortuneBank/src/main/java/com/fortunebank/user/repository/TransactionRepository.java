package com.fortunebank.user.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByFudAccountNumberOrTudAccountNumber(Long accountNumber, Long secondAccountNumber);

}