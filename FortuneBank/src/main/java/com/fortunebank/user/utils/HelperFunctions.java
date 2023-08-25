package com.fortunebank.user.utils;

import com.fortunebank.user.dto.ResponseBeneficiary;
import com.fortunebank.user.dto.ResponseTransaction;
import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.model.Beneficiary;
import com.fortunebank.user.model.Transaction;
import com.fortunebank.user.model.UserDetails;

public class HelperFunctions {
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
        return responseTransaction;
    }

    public static ResponseBeneficiary getResponseBeneficiaryfromPayee(Beneficiary beneficiary) {
        ResponseBeneficiary responseBeneficiary = new ResponseBeneficiary();
        responseBeneficiary.setAccountnumber(beneficiary.getPayeeDetails().getAccountNumber());
        responseBeneficiary.setName(beneficiary.getName());
        responseBeneficiary.setNickname(beneficiary.getNickName());
        responseBeneficiary.setBid(beneficiary.getBid());
        return responseBeneficiary;
    }
}
