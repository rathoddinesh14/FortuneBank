package com.fortunebank.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountNumber) {
        return transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber);
    }
}