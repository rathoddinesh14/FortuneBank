package com.fortunebank.user.service;

import java.util.Optional;

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

    public NetBankingUser registerNetBankingUser(NetBankingUser netBankingUser) {
        return netBankingUserRepository.save(netBankingUser);
    }

    public NetBankingUser loginGetUser(String userId) {
        NetBankingUser nbu = netBankingUserRepository.findByUserId(userId).orElseThrow(
                () -> new InvalidLoginException("User with this userId not found!"));
        return nbu;
    }

    public boolean resetPassword(ForgotPasswordDto fpd) throws ResourceNotFoundException {
        NetBankingUser user = netBankingUserRepository.findByUserId(fpd.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User with this userId not found!"));
        user.setLoginPassword(fpd.getPassword());
        return netBankingUserRepository.save(user) != null ? true : false;
    }

    public String getUserId(ForgotUserIdDto fud) throws ResourceNotFoundException {
        NetBankingUser user = netBankingUserRepository.findByAccountNumber(fud.getAccountnumber())
                .orElseThrow(() -> new ResourceNotFoundException("Account number not found!"));
        return user.getUserId();
    }

    public Long getAccountNumber(Long userid) throws Exception {

        NetBankingUser user = netBankingUserRepository.findById(userid)
                .orElseThrow(() -> new Exception(""));
        return user.getAccountNumber();
    }
}
