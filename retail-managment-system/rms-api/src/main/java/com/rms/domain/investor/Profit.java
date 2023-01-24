package com.rms.domain.investor;

import com.rms.domain.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Profits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profit extends JPAEntity {

    @Column(name = "book_no")
    private String bookNo;

    @Column(name = "profit_amount")
    private Double profitAmount;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_calculated")
    private boolean calculated;


}
