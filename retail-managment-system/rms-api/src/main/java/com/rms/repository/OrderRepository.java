package com.rms.repository;

import com.rms.domain.sales.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
