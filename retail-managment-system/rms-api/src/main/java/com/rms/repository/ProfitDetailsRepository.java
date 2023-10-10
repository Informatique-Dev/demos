package com.rms.repository;

import com.rms.domain.investor.ProfitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfitDetailsRepository extends JpaRepository<ProfitDetails, Integer> {

     @Query("select p from ProfitDetails p where p.product.id = :productId")
     Optional<ProfitDetails> findProfitDetailsByProductId(@Param("productId") Integer productId);
}
