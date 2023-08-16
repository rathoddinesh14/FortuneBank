package com.fortunebank.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.NetBankingUser;

public interface NetBankingUserRepository extends JpaRepository<NetBankingUser, Long> {
	public Optional<NetBankingUser> findByUserId(String userId);

	public Optional<NetBankingUser> findByAccountNumber(Long accountNumber);
}
