package com.fortunebank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fortunebank.user.dto.CustomerSearchDto;
import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.service.AdminService;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/customers")
    public ResponseEntity<List<ResponseUserProfile>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<ResponseTransaction>> getAllTransactions() {
        return ResponseEntity.ok(adminService.getAllTransactions());
    }

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<ResponseBeneficiary>> getAllBeneficiaries() {
        return ResponseEntity.ok(adminService.getAllBeneficiaries());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@Validated @RequestBody UserLoginDto uld) {
        String message = "";
        try {
            if (adminService.loginAdmin(uld)) {
                return ResponseEntity.ok("Login Successful");
            }
        } catch (Exception e) {
            message = e.getMessage();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        return ResponseEntity.badRequest().body(message);
    }

    @PutMapping("/updateaccountstatus/{accountNumber}/{status}")
    public boolean updateAccountStatus(@PathVariable Long accountNumber, @PathVariable String status) {
        return adminService.updateAccountStatus(accountNumber, status);
    }

    @PostMapping("customersearch/firstname")
    public ResponseEntity<List<ResponseUserProfile>> customerFirstNameSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerFirstNameSearch(entity));
    }

    @PostMapping("customersearch/accountstatus")
    public ResponseEntity<List<ResponseUserProfile>> customerSearchByAccountStatus(
            @RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerSearchByAccountStatus(entity));
    }

    @GetMapping("/transactions-between-dates")
    public ResponseEntity<List<ResponseTransaction>> getTransactionsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(adminService.getTransactionsBetweenDates(startDate, endDate));
    }

}
