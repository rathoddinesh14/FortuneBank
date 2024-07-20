package com.fortunebank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.TransactionDto;
import com.fortunebank.user.enumtype.TransactionMode;
import com.fortunebank.user.enumtype.TransactionType;
import com.fortunebank.user.exception.InsufficientBalanceException;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testSaveTransaction() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromaccount(1L);
        transactionDto.setToaccount(2L);
        transactionDto.setAmount(1000.0);
        transactionDto.setRemarks("Test transaction");
        transactionDto.setMaturityInstructions("IMMEDIATE");
        transactionDto.setMode("NEFT");

        UserDetails fromUser = new UserDetails();
        fromUser.setAccountNumber(1L);
        fromUser.setBalance(2000.0);

        UserDetails toUser = new UserDetails();
        toUser.setAccountNumber(2L);
        toUser.setBalance(500.0);

        Transaction transaction = new Transaction();
        transaction.setTid(1L);
        transaction.setAmount(1000.0);
        Date date = Calendar.getInstance().getTime();
        transaction.setDate(date);
        transaction.setFud(fromUser);
        transaction.setMaturityInstructions("IMMEDIATE");
        transaction.setRemark("Test transaction");
        transaction.setTud(toUser);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setTransactionMode(TransactionMode.NEFT);

        when(userRepository.findByAccountNumber(1L)).thenReturn(Optional.of(fromUser));
        when(userRepository.findByAccountNumber(2L)).thenReturn(Optional.of(toUser));
        when(transactionRepository.save(any())).thenReturn(transaction);

        ResponseTransaction savedTransaction = transactionService.saveTransaction(transactionDto);
        
        assertEquals(1000.0, fromUser.getBalance());
        assertEquals(1500.0, toUser.getBalance());
        assertEquals(1L, savedTransaction.getTid());
        assertEquals(1000.0, savedTransaction.getAmount());
        assertEquals(date.toString(), savedTransaction.getDate());
        assertEquals(1L, savedTransaction.getFromAccountNumber());
        assertEquals("IMMEDIATE", savedTransaction.getMaturityInstructions());
        assertEquals("Test transaction", savedTransaction.getRemark());
        assertEquals(2L, savedTransaction.getToAccountNumber());
    }

    @Test
    public void testSaveTransactionWithInsufficientBalance() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromaccount(1L);
        transactionDto.setToaccount(2L);
        transactionDto.setAmount(3000.0);
        transactionDto.setRemarks("Test transaction");
        transactionDto.setMaturityInstructions("IMMEDIATE");
        transactionDto.setMode("NEFT");

        UserDetails fromUser = new UserDetails();
        fromUser.setAccountNumber(1L);
        fromUser.setBalance(2000.0);

        UserDetails toUser = new UserDetails();
        toUser.setAccountNumber(2L);
        toUser.setBalance(500.0);

        when(userRepository.findByAccountNumber(1L)).thenReturn(Optional.of(fromUser));
        when(userRepository.findByAccountNumber(2L)).thenReturn(Optional.of(toUser));

        assertThrows(InsufficientBalanceException.class, () -> transactionService.saveTransaction(transactionDto));

        assertEquals(2000.0, fromUser.getBalance());
        assertEquals(500.0, toUser.getBalance());
        verify(userRepository, times(0)).save(any(UserDetails.class));
        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    public void testSaveTransactionWithInvalidUserAccounts() {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setFromaccount(1L);
        transactionDto.setToaccount(2L);
        transactionDto.setAmount(1000.0);
        transactionDto.setRemarks("Test transaction");
        transactionDto.setMaturityInstructions("IMMEDIATE");
        transactionDto.setMode("NEFT");

        when(userRepository.findByAccountNumber(1L)).thenReturn(Optional.empty());
        when(userRepository.findByAccountNumber(2L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.saveTransaction(transactionDto));

        verify(userRepository, times(0)).save(any(UserDetails.class));
        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    public void testGetTransactions() throws ResourceNotFoundException {
        Long accountNumber = 1L;

        UserDetails user = new UserDetails();
        user.setAccountNumber(accountNumber);

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(1000.0);
        transaction1.setDate(Calendar.getInstance().getTime());
        transaction1.setFud(user);
        transaction1.setMaturityInstructions("IMMEDIATE");
        transaction1.setRemark("Test transaction");
        transaction1.setTud(user);
        transaction1.setTransactionType(TransactionType.TRANSFER);
        transaction1.setTransactionMode(TransactionMode.NEFT);

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(2000.0);
        transaction2.setDate(Calendar.getInstance().getTime());
        transaction2.setFud(user);
        transaction2.setMaturityInstructions("IMMEDIATE");
        transaction2.setRemark("Test transaction");
        transaction2.setTud(user);
        transaction2.setTransactionType(TransactionType.TRANSFER);
        transaction2.setTransactionMode(TransactionMode.NEFT);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber))
                .thenReturn(Optional.of(transactions));

        List<Transaction> result = transactionService.getTransactions(accountNumber);

        assertEquals(transactions, result);
    }

    @Test
    public void testGetTransactionsWithInvalidAccountNumber() {
        Long accountNumber = 1L;

        when(transactionRepository.findByFudAccountNumberOrTudAccountNumber(accountNumber, accountNumber))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransactions(accountNumber));
    }

    @Test
    public void testDepositAmount() {
        Long accountNumber = 1L;
        double amount = 1000.0;

        UserDetails user = new UserDetails();
        user.setAccountNumber(accountNumber);
        user.setBalance(500.0);

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(user));

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setTid(1L);
        expectedTransaction.setAmount(amount);
        expectedTransaction.setDate(Calendar.getInstance().getTime());
        expectedTransaction.setFud(user);
        expectedTransaction.setMaturityInstructions("No");
        expectedTransaction.setRemark("Deposit");
        expectedTransaction.setTud(user);
        expectedTransaction.setTransactionType(TransactionType.DEPOSIT);
        expectedTransaction.setTransactionMode(TransactionMode.SELF);

        when(transactionRepository.save(any())).thenReturn(expectedTransaction);

        Transaction actualTransaction = transactionService.depositAmount(accountNumber, amount);

        assertEquals(user.getBalance(), 1500.0);
        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    public void testWithdrawAmount() {
        Long accountNumber = 1L;
        double amount = 1000.0;

        UserDetails user = new UserDetails();
        user.setAccountNumber(accountNumber);
        user.setBalance(2000.0);

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(user));

        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setTid(1L);
        expectedTransaction.setAmount(amount);
        expectedTransaction.setDate(Calendar.getInstance().getTime());
        expectedTransaction.setFud(user);
        expectedTransaction.setMaturityInstructions("No");
        expectedTransaction.setRemark("Withdrawal");
        expectedTransaction.setTud(user);
        expectedTransaction.setTransactionType(TransactionType.WITHDRAWAL);
        expectedTransaction.setTransactionMode(TransactionMode.SELF);

        when(transactionRepository.save(any())).thenReturn(expectedTransaction);

        Transaction actualTransaction = transactionService.withdrawAmount(accountNumber, amount);

        assertEquals(user.getBalance(), 1000.0);
        assertEquals(expectedTransaction, actualTransaction);
    }

    @Test
    public void testWithdrawAmountWithInvalidAccountNumber() {
        Long accountNumber = 1L;
        double amount = 1000.0;

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> transactionService.withdrawAmount(accountNumber, amount));
    }

    @Test
    public void testWithdrawAmountWithInsufficientBalance() {
        Long accountNumber = 1L;
        double amount = 3000.0;

        UserDetails user = new UserDetails();
        user.setAccountNumber(accountNumber);
        user.setBalance(2000.0);

        when(userRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(user));

        assertThrows(InsufficientBalanceException.class, () -> transactionService.withdrawAmount(accountNumber, amount));

        assertEquals(user.getBalance(), 2000.0);
        verify(userRepository, times(0)).save(any(UserDetails.class));
        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    public void testGetTransactionsBetweenDates() throws ResourceNotFoundException {
        Long userId = 1L;
        Date startDate = new Date();
        Date endDate = new Date();

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(1000.0);
        transaction1.setDate(Calendar.getInstance().getTime());
        transaction1.setFud(new UserDetails());
        transaction1.setMaturityInstructions("IMMEDIATE");
        transaction1.setRemark("Test transaction");
        transaction1.setTud(new UserDetails());
        transaction1.setTransactionType(TransactionType.TRANSFER);
        transaction1.setTransactionMode(TransactionMode.NEFT);

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(2000.0);
        transaction2.setDate(Calendar.getInstance().getTime());
        transaction2.setFud(new UserDetails());
        transaction2.setMaturityInstructions("IMMEDIATE");
        transaction2.setRemark("Test transaction");
        transaction2.setTud(new UserDetails());
        transaction2.setTransactionType(TransactionType.TRANSFER);
        transaction2.setTransactionMode(TransactionMode.NEFT);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findByFudAccountNumberAndDateBetweenOrTudAccountNumberAndDateBetween(
                userId, startDate, endDate, userId, startDate, endDate)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsBetweenDates(userId, startDate, endDate);

        assertEquals(transactions, result);
    }

}