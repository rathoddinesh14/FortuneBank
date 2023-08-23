package com.fortunebank.user.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.TransactionService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.saveTransaction(transactionDto), HttpStatus.OK);
    }

    @GetMapping("/get/{accountNumber}")
    public ResponseEntity<List<ResponseTransaction>> getTransactions(@PathVariable Long accountNumber) {
        List<ResponseTransaction> transactions = new ArrayList<>();
        transactionService.getTransactions(accountNumber).forEach(transaction -> {
            ResponseTransaction responseTransaction = new ResponseTransaction();
            responseTransaction.setTid(transaction.getTid());
            responseTransaction.setAmount(transaction.getAmount());
            responseTransaction.setDate(transaction.getDate().toString());
            responseTransaction.setFromAccountNumber(transaction.getFud().getAccountNumber());
            responseTransaction.setMaturityInstructions(transaction.getMaturityInstructions());
            responseTransaction.setRemark(transaction.getRemark());
            responseTransaction.setToAccountNumber(transaction.getTud().getAccountNumber());
            transactions.add(responseTransaction);
        });
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}