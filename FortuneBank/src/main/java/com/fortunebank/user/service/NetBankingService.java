package com.fortunebank.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortunebank.user.dto.ForgotPasswordDto;
import com.fortunebank.user.dto.ForgotUserIdDto;
import com.fortunebank.user.exception.InvalidLoginException;
import com.fortunebank.user.exception.ResourceNotFoundException;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.repository.NetBankingUserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NetBankingService {

    @Autowired
    private NetBankingUserRepository netBankingUserRepository;

    /**
     * This method is used to register a new user.
     * 
     * @param NetBankingUser netBankingUser
     * @return NetBankingUser object
     */
    public NetBankingUser registerNetBankingUser(NetBankingUser netBankingUser) {
        return netBankingUserRepository.save(netBankingUser);
    }

    /**
     * This method is used to login a user.
     * 
     * @param String userId
     * @param String password
     * @return NetBankingUser object
     * @throws InvalidLoginException
     */
    public NetBankingUser loginGetUser(String userId) throws InvalidLoginException {
        NetBankingUser nbu = netBankingUserRepository.findByUserId(userId).orElseThrow(
                () -> new InvalidLoginException("User with this userId not found!"));
        return nbu;
    }

    /**
     * This method is used to change password of a user.
     * 
     * @param ForgotPasswordDto fpd
     * @return boolean
     * @throws ResourceNotFoundException
     */
    public boolean resetPassword(ForgotPasswordDto fpd) throws ResourceNotFoundException {
        NetBankingUser user = netBankingUserRepository.findByUserId(fpd.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User with this userId not found!"));
        user.setLoginPassword(fpd.getPassword());
        return netBankingUserRepository.save(user) != null ? true : false;
    }

    /**
     * This method is used to get userId of a user
     * when user has forgotten his/her userId.
     * 
     * @param ForgotUserIdDto fud
     * @return String
     * @throws ResourceNotFoundException
     */
    public String getUserId(ForgotUserIdDto fud) throws ResourceNotFoundException {
        NetBankingUser user = netBankingUserRepository.findByAccountNumber(fud.getAccountnumber())
                .orElseThrow(() -> new ResourceNotFoundException("Account number not found!"));
        return user.getUserId();
    }

    /**
     * This method is used to get account number of a user
     * given user id.
     * 
     * @param Long userid
     * @return Long
     * @throws Exception
     */
    public Long getAccountNumber(Long userid) throws Exception {

        NetBankingUser user = netBankingUserRepository.findById(userid)
                .orElseThrow(() -> new Exception(""));
        return user.getAccountNumber();
    }
}
