package com.fortunebank.user.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ResponseUserProfile;
import com.fortunebank.user.dto.UserDetailsDto;
import com.fortunebank.user.enumtype.AccountStatus;
import com.fortunebank.user.model.Address;
import com.fortunebank.user.model.UserDetails;
import com.fortunebank.user.repository.AddressRepository;
import com.fortunebank.user.repository.UserRepository;
import com.fortunebank.user.utils.HelperFunctions;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * This method is used to save user details.
     * 
     * @param UserDetailsDto userDetailsDto
     * @return UserDetails object
     */
    public UserDetails saveUserDetails(UserDetailsDto userDetailsDto) {
        UserDetails user = new UserDetails();
        user.setFirstName(userDetailsDto.getFirstName());
        user.setMiddleName(userDetailsDto.getMiddleName());
        user.setLastName(userDetailsDto.getLastName());
        user.setFatherName(userDetailsDto.getFatherName());
        user.setPhone(userDetailsDto.getPhone());
        user.setEmail(userDetailsDto.getEmail());
        user.setAadharNumber(userDetailsDto.getAadharNumber());
        Date dob = Date.valueOf(userDetailsDto.getDob());
        user.setDob(dob);
        user.setAccountType(0);
        user.setBalance(0);
        user.setAccountStatus(AccountStatus.ENABLED);
        return userRepository.save(user);
    }

    /**
     * This method is used to save temporary address
     * and permanent address of user.
     * 
     * @param UserDetailsDto userDetailsDto
     * @param UserDetails    appliedUser
     */
    public void saveAddress(UserDetailsDto userDto, UserDetails appliedUser) {
        Address tAddress = new Address();
        tAddress.setLine1(userDto.getTaddress().getLine1());
        tAddress.setLine2(userDto.getTaddress().getLine2());
        tAddress.setState(userDto.getTaddress().getState());
        tAddress.setPincode(userDto.getTaddress().getPincode());
        tAddress.setLandmark(userDto.getTaddress().getLandmark());
        tAddress.setCity(userDto.getTaddress().getCity());
        tAddress.setUd(appliedUser);
        tAddress.setType(userDto.getTaddress().getType());
        addressRepository.save(tAddress);

        Address pAddress = new Address();
        pAddress.setLine1(userDto.getPaddress().getLine1());
        pAddress.setLine2(userDto.getPaddress().getLine2());
        pAddress.setState(userDto.getPaddress().getState());
        pAddress.setPincode(userDto.getPaddress().getPincode());
        pAddress.setLandmark(userDto.getPaddress().getLandmark());
        pAddress.setCity(userDto.getPaddress().getCity());
        pAddress.setUd(appliedUser);
        pAddress.setType(userDto.getPaddress().getType());
        addressRepository.save(pAddress);
    }

    /**
     * This method is used to get user profile.
     * 
     * @param Long accountNumber
     * @return ResponseUserProfile object
     */
    public ResponseUserProfile getUserProfile(Long accountNumber) {
        Optional<UserDetails> userDetails = userRepository.findByAccountNumber(accountNumber);

        userDetails.orElseThrow(() -> new RuntimeException("User not found"));

        return HelperFunctions.getResponseUserProfilefromUserDetails(userDetails.get());
    }

    /**
     * This method is used to get name of a customer
     * with given account number.
     * 
     * @param Long accountNumber
     * @return ResponseUserProfile object
     */
    public String getName(Long accountNumber) {
        return userRepository.findByAccountNumber(accountNumber).get().getFirstName();
    }

    /**
     * This method is used to update account status.
     * 
     * @param Long          accountNumber
     * @param AccountStatus status
     * @return boolean
     */
    public boolean updateAccountStatus(Long accountNumber, AccountStatus status) {
        Optional<UserDetails> userDetails = userRepository.findByAccountNumber(accountNumber);
        userDetails.orElseThrow(() -> new RuntimeException("User not found"));
        userDetails.get().setAccountStatus(status);
        userRepository.save(userDetails.get());
        return true;
    }
}
