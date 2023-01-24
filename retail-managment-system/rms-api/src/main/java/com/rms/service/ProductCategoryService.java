package com.rms.service;

import com.rms.domain.core.ProductCategory;
import com.rms.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAll() {
        return productCategoryRepository.findAll();
    }

    public Optional<ProductCategory> getById(Integer id) {
        return productCategoryRepository.findById(id);
    }

    public ProductCategory save(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    public void deleteById(Integer id) {
        productCategoryRepository.deleteById(id);
    }

}
