package com.rms.repository;

import com.rms.domain.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("select p from Product p where p.productCategory.id=:categoryId")
    Page<Product> getByProductCategory(@Param("categoryId") Integer categoryId , Pageable pageable);

    @Query("select p from Product p where p.name=:productName")
    Optional<Product> findByProductName( @Param("productName")  String productName);
}
