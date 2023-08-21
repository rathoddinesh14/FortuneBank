package com.fortunebank.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Beneficiary;


public interface PayeeRepository extends JpaRepository<Beneficiary, Long> {

	List<Beneficiary> findByUdAccountNumber(Long accountNumber);
}
