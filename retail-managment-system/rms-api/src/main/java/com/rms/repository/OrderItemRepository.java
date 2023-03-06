package com.rms.repository;

import com.rms.domain.sales.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = "select oi from OrderItem oi  join fetch oi.order o join fetch oi.product" ,
            countQuery = "select count (o) FROM OrderItem  o")
    Page<OrderItem> findAll(Pageable pageable);



    @Query("select o from OrderItem o where o.order.id = :orderId")
    List<OrderItem> findOrderItemsByOrderId(@Param("orderId") Integer orderId );





}
