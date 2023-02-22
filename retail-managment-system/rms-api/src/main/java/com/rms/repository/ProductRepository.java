package com.rms.repository;

import com.rms.domain.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("select p from Product p where p.productCategory.id=:catId")
    Page<Product> getByProductCategory(@Param("catId") Integer catId , Pageable pageable);

   @Query(value = "select p from Product p left join fetch ProductCategory  pc on p.productCategory.id =pc.id")
    Page<Product> findAll(Pageable pageable);


}
