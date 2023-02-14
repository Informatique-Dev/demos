package com.rms.repository;

import com.rms.domain.core.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store , Integer> {
    @Query(value = "SELECT s FROM Store s inner JOIN FETCH s.responsible v WHERE v.fullName= :responsible OR :responsible IS NULL ",
            countQuery = "SELECT COUNT(s) FROM Store s WHERE s.responsible.fullName= :responsible OR :responsible IS NULL")
    Page<Store> findAll(@Param("responsible") String responsible , Pageable pageable);
}
