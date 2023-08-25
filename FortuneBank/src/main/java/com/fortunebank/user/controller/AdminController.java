package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.repository.PayeeRepository;
import com.fortunebank.user.repository.TransactionRepository;
import com.fortunebank.user.repository.UserRepository;
import com.fortunebank.user.service.UserService;
import com.fortunebank.user.utils.HelperFunctions;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PayeeRepository beneficiaryRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/customers")
    public ResponseEntity<List<ResponseUserProfile>> getAllUsers() {
        List<ResponseUserProfile> users = userRepository.findAll().stream().collect(ArrayList::new, (list, user) -> {
            list.add(HelperFunctions.getResponseUserProfilefromUserDetails(user));
        }, ArrayList::addAll);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<ResponseTransaction>> getAllTransactions() {
        List<ResponseTransaction> transactions = transactionRepository.findAll().stream().collect(ArrayList::new,
                (list, transaction) -> {
                    list.add(HelperFunctions.getResponseTransactionfromTransaction(transaction));
                }, ArrayList::addAll);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<ResponseBeneficiary>> getAllBeneficiaries() {
        List<ResponseBeneficiary> users = beneficiaryRepository.findAll().stream().collect(ArrayList::new,
                (list, user) -> {
                    list.add(HelperFunctions.getResponseBeneficiaryfromPayee(user));
                }, ArrayList::addAll);

        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public boolean loginUser(@Validated @RequestBody UserLoginDto uld) throws Exception {
        if (uld.getUserid().equals("admin") && uld.getPassword().equals("admin")) {
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/updateaccountstatus/{accountNumber}/{status}")
    public boolean updateAccountStatus(@PathVariable Long accountNumber, @PathVariable String status) {
        return userService.updateAccountStatus(accountNumber, status);
    }
}
