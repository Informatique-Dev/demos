package com.rms.repository;

import com.rms.domain.core.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @EntityGraph("Product.categories")
    List<Product> findAll();

    @Query("select p from Product p where p.productCategory.id=:catId")
    List<Product> getByProductCategory(@Param("catId") Integer catId);

}
