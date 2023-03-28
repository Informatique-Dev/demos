package com.rms.repository;

import com.rms.domain.sales.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select o from Order o join fetch o.employee join fetch  o.customer " ,
            countQuery = "select count (o) FROM Order  o")
    Page<Order> findAll(Pageable pageable);
}
