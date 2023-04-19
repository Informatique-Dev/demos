package com.rms.repository;

import com.rms.domain.investor.ProfitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfitDetailsRepository extends JpaRepository<ProfitDetails, Integer> {
}
