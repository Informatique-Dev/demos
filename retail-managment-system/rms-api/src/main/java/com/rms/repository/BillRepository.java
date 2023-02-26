package com.rms.repository;

import com.rms.domain.core.Employee;
import com.rms.domain.purchase.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    @Query(value = "select b from Bill b where b.billNo= :billNo")
    Optional<Bill> findBillNumber(@Param("billNo") String billNo);




}
