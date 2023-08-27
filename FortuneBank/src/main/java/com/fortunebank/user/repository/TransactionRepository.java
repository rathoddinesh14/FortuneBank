package com.fortunebank.user.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fortunebank.user.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByFudAccountNumberOrTudAccountNumber(Long accountNumber, Long secondAccountNumber);

    List<Transaction> findByFudAccountNumberAndDateBetweenOrTudAccountNumberAndDateBetween(Long userId, Date startDate,
            Date endDate, Long userId2, Date startDate2, Date endDate2);

    @Query("SELECT t FROM Transaction t WHERE t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}