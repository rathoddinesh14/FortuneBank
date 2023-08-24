package com.fortunebank.user.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.enumtype.TransactionType;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Timestamp getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        return Timestamp.valueOf(localDateTime.atZone(zoneId).toLocalDateTime());
    }

    public Transaction saveTransaction(TransactionDto transactionDto) {

        UserDetails fromUser = userRepository.findByAccountNumber(transactionDto.getFromaccount())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user accounts"));

        UserDetails toUser = userRepository.findByAccountNumber(transactionDto.getToaccount())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user accounts"));

        double amount = transactionDto.getAmount();
        if (fromUser.getBalance() < amount) {
            // Insufficient balance, handle appropriately
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Update sender's balance
        fromUser.setBalance(fromUser.getBalance() - amount);
        // Update receiver's balance
        toUser.setBalance(toUser.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setFud(fromUser);
        transaction.setTud(toUser);
        transaction.setAmount(amount);
        transaction.setRemark(transactionDto.getRemarks());
        transaction.setDate(getCurrentTime());
        transaction.setMaturityInstructions(transactionDto.getMaturityInstructions());
        transaction.setTransactionType(TransactionType.TRANSFER);

        // Save the updated balances and the transaction
        userRepository.save(fromUser);
        userRepository.save(toUser);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountNumber) {
        return transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber);
    }

    public boolean depositAmount(Long accountNumber, double amount) {

        UserDetails user = userRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new IllegalArgumentException("Invalid user account"));

        // Update user's balance
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setFud(user);
        transaction.setTud(user);
        transaction.setAmount(amount);
        transaction.setRemark("Deposit");
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setDate(getCurrentTime());
        transaction.setMaturityInstructions("No");

        transactionRepository.save(transaction);
        return true;
    }

    public boolean withdrawAmount(Long accountNumber, double amount) {

        UserDetails user = userRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new IllegalArgumentException("Invalid user account"));

        if (user.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        // Update user's balance
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        Transaction transaction = new Transaction();
        transaction.setFud(user);
        transaction.setTud(user);
        transaction.setAmount(amount);
        transaction.setRemark("Withdrawal");
        transaction.setTransactionType(TransactionType.WITHDRAWAL);
        transaction.setDate(getCurrentTime());
        transaction.setMaturityInstructions("No");

        transactionRepository.save(transaction);
        return true;
    }

}