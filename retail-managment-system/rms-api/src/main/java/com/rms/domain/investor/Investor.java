package com.rms.domain.investor;

import com.rms.domain.common.Person;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Investors")
@Data
public class Investor extends Person {


    @Column(name = "investor_type")
    @Enumerated(EnumType.STRING)
    private InvestorType investorType;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "start_date")
    private LocalDate startDate;

}
