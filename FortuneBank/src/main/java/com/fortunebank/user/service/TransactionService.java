package com.fortunebank.user.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.TransactionDto;
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

        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        transaction.setDate(java.sql.Timestamp.valueOf(localDateTime.atZone(zoneId).toLocalDateTime()));
        transaction.setMaturityInstructions(transactionDto.getMaturityInstructions());

        // Save the updated balances and the transaction
        userRepository.save(fromUser);
        userRepository.save(toUser);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactions(Long accountNumber) {
        return transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber);
    }
}