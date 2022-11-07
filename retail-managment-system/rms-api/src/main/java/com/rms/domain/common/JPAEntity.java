package com.rms.domain.common;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class JPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    private Integer version;
}
