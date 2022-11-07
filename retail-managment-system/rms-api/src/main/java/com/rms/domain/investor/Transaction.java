package com.rms.domain.investor;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Transactions")
@Data
public class Transaction extends JPAEntity {
    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;


}
