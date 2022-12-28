package com.rms.domain.core;

import com.rms.domain.common.JPAEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Product.categories",
                attributeNodes = @NamedAttributeNode("productCategory")
        )
})
@Data
public class Product extends JPAEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "model_no")
    private String modelNo;
    @Column(name = "Brand")
    private String brand;
    @Column(name = "cashPrice")
    private Double cashPrice;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;
}
