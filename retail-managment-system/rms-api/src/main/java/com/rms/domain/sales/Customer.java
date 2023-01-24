package com.rms.domain.sales;

import com.rms.domain.common.Person;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
@Data
public class Customer extends Person {

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "trust_Receipt_No")
    private Integer trustReceiptNo;

}
