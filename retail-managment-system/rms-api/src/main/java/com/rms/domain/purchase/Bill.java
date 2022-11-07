package com.rms.domain.purchase;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bills")
@Data
public class Bill extends JPAEntity {

    @Column(name = "bill_no")
    private String billNo;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Column(name = "recipient")
    private String recipient;
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
