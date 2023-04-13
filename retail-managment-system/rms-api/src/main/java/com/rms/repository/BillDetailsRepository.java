package com.rms.repository;
import com.rms.domain.purchase.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BillDetailsRepository extends JpaRepository<BillDetails, Integer> {

    @Query("select b from BillDetails b where b.bill.id = :billId")
    List<BillDetails>getBillDetailsByBillID(@Param("billId")Integer billId);
}
