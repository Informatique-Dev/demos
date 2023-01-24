package com.rms.domain.core;

import com.rms.domain.common.JPAEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ProductCategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends JPAEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

}
