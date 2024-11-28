package com.rms.repository;

import com.rms.domain.sales.Installment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Integer> {

    @Query("select i from Installment i where ( i.dueDate BETWEEN :startDate AND :endDate ) OR " +
            "( i.paymentDate IS NULL AND i.dueDate <=:startDate ) Order by i.dueDate")
    Page<Installment> findByDueDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query("select i from Installment i JOIN i.order o JOIN o.customer c " +
            "WHERE c.id=:customerId   Order by i.dueDate")
    List<Installment> findByCustomerId(@Param("customerId") Integer customerId);


    @Query(value = "select i from Installment i join i.order o join o.customer c where c.nationalId = :nationalId")
    Page<Installment> findByCustomerNationalId(@Param("nationalId") String nationalId, Pageable pageable);

    @Query("select i from Installment i where i.order.id = :orderId")
    List<Installment> findByOrderId(@Param("orderId") Integer orderId);


}
