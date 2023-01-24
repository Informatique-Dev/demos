package com.rms.domain.purchase;

import com.rms.domain.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
