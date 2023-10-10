package com.rms.domain.investor;

import com.rms.domain.common.JPAEntity;
import com.rms.domain.core.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Profit_Details")
public class ProfitDetails extends JPAEntity {
    @Column(name = "Bill_Price")
    private double billPrice;

    @Column(name = "Product_Price")
    private double productPrice;

    @Column(name = "count")
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
