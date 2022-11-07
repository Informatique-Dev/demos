package com.rms.domain.core;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ProductCategories")
@Data
public class ProductCategory extends JPAEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private Short status;

}
