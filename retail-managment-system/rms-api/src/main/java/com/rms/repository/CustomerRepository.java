package com.rms.repository;

import com.rms.domain.sales.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query(value = "select c from Customer c where c.nationalId= :nationalId")
    Optional<Customer> findByNationalId(String nationalId);

    @Query(value = "select c from Customer c where c.customerCode= :customerCode")
    Optional<Customer> findByCustomerCode(String  customerCode);

    }

