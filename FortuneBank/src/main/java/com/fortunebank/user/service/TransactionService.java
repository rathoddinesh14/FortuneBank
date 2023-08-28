package com.fortunebank.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.exception.InsufficientBalanceException;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.enumtype.TransactionMode;
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

        /**
         * This method is used to save transaction.
         * 
         * @param TransactionDto transactionDto
         * @return Transaction object
         */
        public ResponseTransaction saveTransaction(TransactionDto transactionDto) {

                UserDetails fromUser = userRepository.findByAccountNumber(transactionDto.getFromaccount())
                                .orElseThrow(() -> new ResourceNotFoundException("Invalid user accounts"));

                UserDetails toUser = userRepository.findByAccountNumber(transactionDto.getToaccount())
                                .orElseThrow(() -> new ResourceNotFoundException("Invalid user accounts"));

                double amount = transactionDto.getAmount();
                if (fromUser.getBalance() < amount) {
                        // Insufficient balance, handle appropriately
                        throw new InsufficientBalanceException("Insufficient balance");
                }

                // Update sender's balance
                fromUser.setBalance(fromUser.getBalance() - amount);
                // Update receiver's balance
                toUser.setBalance(toUser.getBalance() + amount);

                // Save the updated balances and the transaction
                userRepository.save(fromUser);
                userRepository.save(toUser);
                return HelperFunctions.getResponseTransactionfromTransaction(
                                transactionRepository.save(
                                                HelperFunctions.getTransaction(fromUser, toUser, amount,
                                                                transactionDto.getRemarks(), TransactionType.TRANSFER,
                                                                transactionDto.getMaturityInstructions(),
                                                                TransactionMode.valueOf(transactionDto.getMode()))));
        }

        /**
         * This method is used to get all transactions of a user.
         * 
         * @param Long accountNumber
         * @return List of Transaction objects
         * @throws ResourceNotFoundException
         */
        public List<Transaction> getTransactions(Long accountNumber) throws ResourceNotFoundException {
                return transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber)
                                .orElseThrow(() -> new ResourceNotFoundException("Account number not found!"));
        }

        /**
         * This method is used to deposit amount.
         * 
         * @param Long   accountNumber
         * @param double amount
         * @return Transaction object
         */
        public Transaction depositAmount(Long accountNumber, double amount) {

                UserDetails user = userRepository.findByAccountNumber(accountNumber).orElseThrow(
                                () -> new IllegalArgumentException("Invalid user account"));

                // Update user's balance
                user.setBalance(user.getBalance() + amount);
                userRepository.save(user);

                return transactionRepository
                                .save(HelperFunctions.getTransaction(user, user, amount, "Deposit",
                                                TransactionType.DEPOSIT,
                                                "No",
                                                TransactionMode.SELF));
        }

        /**
         * This method is used to withdraw amount.
         * 
         * @param Long   accountNumber
         * @param double amount
         * @return Transaction object
         */
        public Transaction withdrawAmount(Long accountNumber, double amount) {

                UserDetails user = userRepository.findByAccountNumber(accountNumber).orElseThrow(
                                () -> new IllegalArgumentException("Invalid user account"));

                if (user.getBalance() < amount) {
                        throw new InsufficientBalanceException("Insufficient balance");
                }

                // Update user's balance
                user.setBalance(user.getBalance() - amount);
                userRepository.save(user);

                return transactionRepository.save(
                                HelperFunctions.getTransaction(user, user, amount, "Withdrawal",
                                                TransactionType.WITHDRAWAL, "No", TransactionMode.SELF));
        }

        /**
         * This method is used to get transactions between dates.
         * 
         * @param Long accountNumber
         * @param Date startDate
         * @param Date endDate
         * @return List of Transaction objects
         */
        public List<Transaction> getTransactionsBetweenDates(Long userId, Date startDate, Date endDate) {
                return transactionRepository.findByFudAccountNumberAndDateBetweenOrTudAccountNumberAndDateBetween(
                                userId, startDate, endDate, userId, startDate, endDate);
        }
}