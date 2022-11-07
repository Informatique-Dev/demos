package com.rms.domain.common;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Persons")
@Data
public class Person extends JPAEntity {

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

}
