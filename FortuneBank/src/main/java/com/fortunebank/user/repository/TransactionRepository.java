package com.fortunebank.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByFudAccountNumberOrTudAccountNumber(Long accountNumber);

}