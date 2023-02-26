package com.rms.repository;

import com.rms.domain.sales.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

 @Query("select C from Customer C where C.trustReceiptNo=:trustReceiptNo ")
    Optional<Customer> findTrustReceiptNo(@Param("trustReceiptNo") Integer trustReceiptNo);

 }
