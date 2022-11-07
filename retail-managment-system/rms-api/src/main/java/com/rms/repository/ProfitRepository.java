package com.rms.repository;

import com.rms.domain.investor.Profit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitRepository extends JpaRepository<Profit, Integer> {
    @Query("select p from Profit p where p.calculated=:calculated")
    List<Profit> getByCalculated(@Param("calculated") Boolean calculated);
}
