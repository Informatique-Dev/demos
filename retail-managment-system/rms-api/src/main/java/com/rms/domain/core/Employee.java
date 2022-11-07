package com.rms.domain.core;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
@Data
public class Employee extends JPAEntity {

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "job")
    private String job;
    @Column(name = "national_id")
    private String nationalId;
    @Column(name = "primary_phone_no")
    private String primaryPhoneNo;
    @Column(name = "secondary_phone_no")
    private String secondaryPhoneNo;
    @Column(name = "address")
    private String address;

}
