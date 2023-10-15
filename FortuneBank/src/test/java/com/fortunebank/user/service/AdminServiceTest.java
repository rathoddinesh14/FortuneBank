package com.fortunebank.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private PayeeRepository payeeRepository;

    @MockBean
    private UserService userService;

    UserDetails user1 = null;
    UserDetails user2 = null;

    @BeforeEach
    public void setUp() {
        user1 = new UserDetails();
        user1.setAccountNumber(1L);
        user1.setFirstName("John");
        user1.setDob(Date.valueOf("2000-01-01"));
        user1.setAccountStatus(AccountStatus.ENABLED);

        user2 = new UserDetails();
        user2.setAccountNumber(2L);
        user2.setFirstName("Jane");
        user2.setDob(Date.valueOf("2000-01-01"));
        user2.setAccountStatus(AccountStatus.ENABLED);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        user2 = null;
    }


    @Test
    public void testGetAllUsers() {
        List<UserDetails> users = new ArrayList<>();
        
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<ResponseUserProfile> responseUsers = adminService.getAllUsers();

        assertEquals(2, responseUsers.size());
        assertEquals("John", responseUsers.get(0).getFirstName());
        assertEquals(1L, responseUsers.get(0).getAccountNumber());
        assertEquals("Jane", responseUsers.get(1).getFirstName());
        assertEquals(2L, responseUsers.get(1).getAccountNumber());
    }

    @Test
    public void testGetAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setTid(1L);
        transaction1.setAmount(1000.0);
        transaction1.setDate(Date.valueOf("2020-01-01"));
        transaction1.setFud(user1);
        transaction1.setTud(user2);

        Transaction transaction2 = new Transaction();
        transaction2.setTid(2L);
        transaction2.setAmount(2000.0);
        transaction2.setDate(Date.valueOf("2020-01-01"));
        transaction2.setFud(user2);
        transaction2.setTud(user1);

        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<ResponseTransaction> responseTransactions = adminService.getAllTransactions();

        assertEquals(2, responseTransactions.size());
        assertEquals(1L, responseTransactions.get(0).getTid());
        assertEquals(1000.0, responseTransactions.get(0).getAmount());
        assertEquals(2L, responseTransactions.get(1).getTid());
        assertEquals(2000.0, responseTransactions.get(1).getAmount());
    }

}
