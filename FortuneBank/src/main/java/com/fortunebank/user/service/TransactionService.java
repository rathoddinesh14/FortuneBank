package com.fortunebank.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.enumtype.TransactionType;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;
import com.fortunebank.user.utils.HelperFunctions;

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

        // Save the updated balances and the transaction
        userRepository.save(fromUser);
        userRepository.save(toUser);
        return transactionRepository.save(HelperFunctions.getTransaction(fromUser, toUser, amount,
                transactionDto.getRemarks(), TransactionType.TRANSFER, transactionDto.getMaturityInstructions()));
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

        transactionRepository
                .save(HelperFunctions.getTransaction(user, user, amount, "Deposit", TransactionType.DEPOSIT,
                        "No"));
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

        transactionRepository.save(
                HelperFunctions.getTransaction(user, user, amount, "Withdrawal", TransactionType.WITHDRAWAL, "No"));
        return true;
    }

    public List<Transaction> getTransactionsBetweenDates(Long userId, Date startDate, Date endDate) {
        return transactionRepository.findByFudAccountNumberAndDateBetweenOrTudAccountNumberAndDateBetween(
                userId, startDate, endDate, userId, startDate, endDate);
    }
}