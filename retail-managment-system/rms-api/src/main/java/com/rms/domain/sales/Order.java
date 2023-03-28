package com.rms.domain.sales;

import com.rms.domain.common.AuditingEntity;
import com.rms.domain.core.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AuditingEntity {

    @Temporal(TemporalType.DATE)
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "paid_amount")
    private Double paidAmount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "remaining_amount")
    private Double remainingAmount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
