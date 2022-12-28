package com.rms.domain.sales;

import com.rms.domain.common.JPAEntity;
import com.rms.domain.core.Employee;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
public class Order extends JPAEntity {

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "payment_Type")
    private Short paymentType;
    @Column(name = "paid_amount")
    private Double paidAmount;
    @Column(name = "remaining_amount")
    private Double remainingAmount;
    @Column(name = "installment_amount")
    private Double installmentAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItems;

}
