package com.fortunebank.user.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.enumtype.TransactionMode;
import com.fortunebank.user.enumtype.TransactionType;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;

public class HelperFunctions {

    /**
     * This method is used to get ResponseUserProfile object from UserDetails.
     * 
     * @param UserDetails userDetails
     * @return ResponseUserProfile object
     */
    public static ResponseUserProfile getResponseUserProfilefromUserDetails(UserDetails userDetails) {
        ResponseUserProfile responseUserProfile = new ResponseUserProfile();
        responseUserProfile.setAccountNumber(userDetails.getAccountNumber());
        responseUserProfile.setFirstName(userDetails.getFirstName());
        responseUserProfile.setMiddleName(userDetails.getMiddleName());
        responseUserProfile.setLastName(userDetails.getLastName());
        responseUserProfile.setFatherName(userDetails.getFatherName());
        responseUserProfile.setPhone(userDetails.getPhone());
        responseUserProfile.setEmail(userDetails.getEmail());
        responseUserProfile.setAadharNumber(userDetails.getAadharNumber());
        responseUserProfile.setDob(userDetails.getDob().toString());
        responseUserProfile.setAccountType(userDetails.getAccountType());
        responseUserProfile.setBalance(userDetails.getBalance());
        responseUserProfile.setAccountStatus(userDetails.getAccountStatus().getDisplayName());
        return responseUserProfile;
    }

    /**
     * This method is used to get ResponseTransaction object from Transaction.
     * 
     * @param Transaction transaction
     * @return ResponseTransaction object
     */
    public static ResponseTransaction getResponseTransactionfromTransaction(Transaction transaction) {
        ResponseTransaction responseTransaction = new ResponseTransaction();
        responseTransaction.setTid(transaction.getTid());
        responseTransaction.setAmount(transaction.getAmount());
        responseTransaction.setDate(transaction.getDate().toString());
        responseTransaction.setFromAccountNumber(transaction.getFud().getAccountNumber());
        responseTransaction.setMaturityInstructions(
                transaction.getMaturityInstructions() == null ? "NA" : transaction.getMaturityInstructions());
        responseTransaction.setRemark(transaction.getRemark());
        responseTransaction.setToAccountNumber(transaction.getTud().getAccountNumber());
        responseTransaction.setTransactionType(transaction.getTransactionType());
        responseTransaction.setTransactionMode(transaction.getTransactionMode());
        return responseTransaction;
    }

    /**
     * This method is used to get ResponseBeneficiary object from Beneficiary.
     * 
     * @param Beneficiary beneficiary
     * @return ResponseBeneficiary object
     */
    public static ResponseBeneficiary getResponseBeneficiaryfromPayee(Beneficiary beneficiary) {
        ResponseBeneficiary responseBeneficiary = new ResponseBeneficiary();
        responseBeneficiary.setPayeeAccountNumber(beneficiary.getPayeeDetails().getAccountNumber());
        responseBeneficiary.setName(beneficiary.getName());
        responseBeneficiary.setNickName(beneficiary.getNickName());
        responseBeneficiary.setBid(beneficiary.getBid());
        responseBeneficiary.setAccountNumber(beneficiary.getUd().getAccountNumber());
        return responseBeneficiary;
    }

    /**
     * This method is used to get Timestamp object of current time.
     * 
     * @param
     * @return Timestamp object
     */
    public static Timestamp getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");
        return Timestamp.valueOf(localDateTime.atZone(zoneId).toLocalDateTime());
    }

    /**
     * This method is used to get Transaction object.
     * 
     * @param UserDetails     fromUser
     * @param UserDetails     toUser
     * @param double          amount
     * @param String          remark
     * @param TransactionType transactionType
     * @param String          maturityInstructions
     * @return Transaction object
     */
    public static Transaction getTransaction(UserDetails fromUser, UserDetails toUser, double amount, String remark,
            TransactionType transactionType, String maturityInstructions, TransactionMode mode) {
        Transaction transaction = new Transaction();
        transaction.setFud(fromUser);
        transaction.setTud(toUser);
        transaction.setAmount(amount);
        transaction.setRemark(remark);
        transaction.setTransactionType(transactionType);
        transaction.setDate(getCurrentTime());
        transaction.setMaturityInstructions(maturityInstructions);
        transaction.setTransactionMode(mode);
        return transaction;
    }

    /**
     * This method is used to get ResponseTransaction object.
     * 
     * @param Double          amount
     * @param Long            fromAccountNumber
     * @param Long            toAccountNumber
     * @param String          message
     * @param TransactionType transactionType
     * @param TransactionMode mode
     * @return ResponseTransaction object
     */
    public static ResponseTransaction getResponseTransaction(Double amount, Long fromAccountNumber,
            Long toAccountNumber, String message, TransactionType transactionType, TransactionMode mode) {
        ResponseTransaction responseTransaction = new ResponseTransaction();
        responseTransaction.setAmount(amount);
        responseTransaction.setFromAccountNumber(fromAccountNumber);
        responseTransaction.setToAccountNumber(toAccountNumber);
        responseTransaction.setTransactionType(transactionType);
        responseTransaction.setRemark(message);
        responseTransaction.setDate(HelperFunctions.getCurrentTime().toString());
        responseTransaction.setTid(0L);
        responseTransaction.setMaturityInstructions("None");
        responseTransaction.setTransactionMode(mode);
        return responseTransaction;
    }
}
