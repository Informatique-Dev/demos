package com.rms.service;

import com.rms.domain.core.ProductCategory;
import com.rms.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getById(Integer id) {
        return productCategoryRepository.getOne(id);
    }

    public ProductCategory save(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

}
