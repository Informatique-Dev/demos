package com.rms.domain.core;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Stores")
@Data
public class Store extends JPAEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    private Employee responsible;
}
