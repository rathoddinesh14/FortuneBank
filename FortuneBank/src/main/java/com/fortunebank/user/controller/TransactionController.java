package com.fortunebank.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.AmountDto;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.service.TransactionService;
import com.fortunebank.user.utils.HelperFunctions;

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
            transactions.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
        });
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositAmount(@RequestBody AmountDto request) {
        Long accountNumber = request.getAccountNumber();
        double depositAmount = request.getAmount();
        if (transactionService.depositAmount(accountNumber, depositAmount)) {
            return ResponseEntity.ok("Deposit successful");
        } else {
            return ResponseEntity.ok("Deposit failed");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawAmount(@RequestBody AmountDto request) {
        Long accountNumber = request.getAccountNumber();
        double withdrawalAmount = request.getAmount();

        try {
            if (transactionService.withdrawAmount(accountNumber, withdrawalAmount)) {
                return ResponseEntity.ok("Withdrawal successful");
            } else {
                return ResponseEntity.ok("Withdrawal failed");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}