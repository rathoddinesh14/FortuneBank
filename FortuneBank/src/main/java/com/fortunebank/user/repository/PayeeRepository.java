package com.fortunebank.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Beneficiary;


public interface PayeeRepository extends JpaRepository<Beneficiary, Long> {
//	public Optional<NetBankingUser> findByAccountNumber(Long accountNumber);
	public Optional<List<Payee>> findByUd_
}
