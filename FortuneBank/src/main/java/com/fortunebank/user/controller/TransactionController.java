package com.fortunebank.user.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        UserDetails fromUser = new UserDetails();
        fromUser.setAccountNumber(transactionDto.getFromAccount());
        UserDetails toUser = new UserDetails();
        toUser.setAccountNumber(transactionDto.getToAccount());
        transaction.setFud(fromUser);
        transaction.setTud(toUser);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setRemark(transactionDto.getRemarks());
        // indian time
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        transaction.setDate(java.sql.Timestamp.valueOf(localDateTime.atZone(zoneId).toLocalDateTime()));
        // transaction.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.OK);
    }

    @GetMapping("/get/{accountNumber}")
    public List<Transaction> getTransactions(@PathVariable Long accountNumber) {
        return transactionService.getTransactions(accountNumber);
    }

}