package com.rms.domain.security;

import com.rms.domain.common.JPAEntity;
import com.rms.domain.core.Employee;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User extends JPAEntity {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee ;
}
