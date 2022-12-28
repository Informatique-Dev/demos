package com.rms.domain.purchase;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Suppliers")
@Data
public class Supplier extends JPAEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "primary_phone_no")
    private String primaryPhoneNo;
    @Column(name = "secondary_phone_no")
    private String secondaryPhoneNo;
    @Column(name = "address")
    private String address;

}
