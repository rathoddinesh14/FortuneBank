package com.fortunebank.user.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fortunebank.user.model.Beneficiary;


public interface PayeeRepository extends JpaRepository<Beneficiary, Long> {

	Optional<List<Beneficiary>> findByUdAccountNumber(Long accountNumber);
}
