package com.rms.repository;

import com.rms.domain.investor.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Integer> {

    @Query("select i from Investor i where i.nationalId = :nationalId")
    Optional<Investor> findByNationalId(@Param("nationalId") String nationalId);
}
