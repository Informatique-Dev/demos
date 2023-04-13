package com.rms.repository;

import com.rms.domain.core.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    @Query(value = "select pc from ProductCategory pc where pc.name= :name" )
    Optional<ProductCategory> findByName(@Param("name")String name);
}
