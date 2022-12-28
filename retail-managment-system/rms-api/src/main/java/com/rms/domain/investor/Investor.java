package com.rms.domain.investor;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Investors")
@Data
public class Investor extends JPAEntity {

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "primary_phone_no")
    private String primaryPhoneNo;
    @Column(name = "secondary_phone_no")
    private String secondaryPhoneNo;
    @Column(name = "address")
    private String address;
    //////
    @Column(name = "investor_type")
    @Enumerated(EnumType.STRING)
    private InvestorType investorType;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "start_date")
    private LocalDate startDate;

}
