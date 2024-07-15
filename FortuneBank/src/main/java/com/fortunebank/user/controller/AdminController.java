package com.fortunebank.user.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /*
     * This method is used to all the users
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @GetMapping("/customers")
    public ResponseEntity<List<ResponseUserProfile>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    /*
     * This method is used to get all the transactions
     * 
     * @return ResponseEntity<List<ResponseTransaction>>
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<ResponseTransaction>> getAllTransactions() {
        return ResponseEntity.ok(adminService.getAllTransactions());
    }

    /*
     * This method is used to get all the beneficiaries
     * 
     * @return ResponseEntity<List<ResponseBeneficiary>>
     */
    @GetMapping("/beneficiaries")
    public ResponseEntity<List<ResponseBeneficiary>> getAllBeneficiaries() {
        return ResponseEntity.ok(adminService.getAllBeneficiaries());
    }

    /*
     * This method is used for login of admin
     * 
     * @param UserLoginDto uld
     * 
     * @return ResponseEntity<String>
     */
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

    /*
     * This method is used to update the account status
     * of the user - enable or disable
     * 
     * @param Long accountNumber
     * 
     * @param String status
     * 
     * @return boolean
     */
    @PutMapping("/updateaccountstatus/{accountNumber}/{status}")
    public boolean updateAccountStatus(@PathVariable Long accountNumber, @PathVariable String status) {
        return adminService.updateAccountStatus(accountNumber, status);
    }

    /*
     * This method is used to search the user by account number
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/accountnumber")
    public ResponseEntity<List<ResponseUserProfile>> customerAccountNumberSearch(
            @RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerAccountNumberSearch(entity));
    }

    /*
     * This method is used to search the user by first name
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<ResponseUserProfile>
     */
    @PostMapping("customersearch/firstname")
    public ResponseEntity<List<ResponseUserProfile>> customerFirstNameSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerFirstNameSearch(entity));
    }

    /*
     * This method is used to search the user by middle name
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/middlename")
    public ResponseEntity<List<ResponseUserProfile>> customerMiddleNameSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerMiddleNameSearch(entity));
    }

    /*
     * This method is used to search the user by last name
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/lastname")
    public ResponseEntity<List<ResponseUserProfile>> customerLastNameSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerLastNameSearch(entity));
    }

    /*
     * This method is used to search the user by father's name
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/fathername")
    public ResponseEntity<List<ResponseUserProfile>> customerFatherNameSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerFatherNameSearch(entity));
    }

    /*
     * This method is used to search the user by phone number
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/phone")
    public ResponseEntity<List<ResponseUserProfile>> customerPhoneSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerPhoneSearch(entity));
    }

    /*
     * This method is used to search the user by email
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/email")
    public ResponseEntity<List<ResponseUserProfile>> customerEmailSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerEmailSearch(entity));
    }

    /*
     * This method is used to search the user by Aadhar number
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<List<ResponseUserProfile>>
     */
    @PostMapping("customersearch/aadharnumber")
    public ResponseEntity<List<ResponseUserProfile>> customerAadharNumberSearch(@RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerAadharNumberSearch(entity));
    }

    /*
     * This method is used to search the user by account status
     * 
     * @param CustomerSearchDto entity
     * 
     * @return ResponseEntity<ResponseUserProfile>
     */
    @PostMapping("customersearch/accountstatus")
    public ResponseEntity<List<ResponseUserProfile>> customerSearchByAccountStatus(
            @RequestBody CustomerSearchDto entity) {
        return ResponseEntity.ok(adminService.customerSearchByAccountStatus(entity));
    }

    /*
     * This method is used to get the transactions between dates
     * 
     * @param Date startDate
     * 
     * @param Date endDate
     * 
     * @return ResponseEntity<List<ResponseTransaction>>
     */
    @GetMapping("/transactions-between-dates")
    public ResponseEntity<List<ResponseTransaction>> getTransactionsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return ResponseEntity.ok(adminService.getTransactionsBetweenDates(startDate, endDate));
    }

}
