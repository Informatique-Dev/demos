package com.rms.domain.sales;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
@Data
public class Customer extends JPAEntity {

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
    @Column(name = "customer_code")
    private String customerCode;
    @Column(name = "trust_Receipt_No")
    private Integer trustReceiptNo;

}
