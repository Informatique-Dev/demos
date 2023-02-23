package com.rms.domain.purchase;

import com.rms.domain.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Bills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends JPAEntity {

    @Column(name = "bill_no")
    private String billNo;


    @Column(name = "date")
    private LocalDate date;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
