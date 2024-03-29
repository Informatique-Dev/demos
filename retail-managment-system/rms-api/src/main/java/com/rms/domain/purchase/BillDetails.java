package com.rms.domain.purchase;

import com.rms.domain.common.JPAEntity;
import com.rms.domain.core.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Bill_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDetails extends JPAEntity {

    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bill_id")
    private Bill bill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

}
