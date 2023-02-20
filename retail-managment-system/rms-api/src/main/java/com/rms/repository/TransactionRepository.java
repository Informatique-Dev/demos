package com.rms.repository;

import com.rms.domain.investor.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
/*@Query(value = "SELECT s FROM Store s inner JOIN FETCH s.responsible v WHERE v.fullName= :responsible OR :responsible IS NULL ",
            countQuery = "SELECT COUNT(s) FROM Store s WHERE s.responsible.fullName= :responsible OR :responsible IS NULL")*/

    @Query(value = "select t from Transaction t inner join fetch  t.investor i where  i.fullName = :investor OR :investor is null " ,
    countQuery = "select count (t) from Transaction t where t.investor.fullName = :investor or :investor is null ")
    Page<Transaction> findAll(@Param("investor") String investor , Pageable pageable);
}
