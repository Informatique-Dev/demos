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

    @Query( value = "SELECT s FROM Store s INNER JOIN FETCH s.responsible v WHERE v.fullName = :responsible",
            countQuery ="SELECT count(s) FROM Store s WHERE s.responsible.fullName = :responsible")
    public Page<Store> findStoreByResponsible(@Param("responsible") String responsible , Pageable pageable);
}
