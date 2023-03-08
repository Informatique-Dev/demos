package com.rms.domain.sales;

import com.rms.domain.common.JPAEntity;
import lombok.Data;
import com.rms.domain.sales.PaymentType;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Installments")
@Data
public class Installment extends JPAEntity {

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "payment_amount")
    private Double paymentAmount;

    @Column(name = "remaining_amount")
    private Double remainingAmount;
    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "status")
    private Short status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
