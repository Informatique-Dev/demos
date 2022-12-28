package com.rms.domain.sales;

import com.rms.domain.common.JPAEntity;
import com.rms.domain.core.Product;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Order_Items")
@Data
public class OrderItem extends JPAEntity {

    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


}
