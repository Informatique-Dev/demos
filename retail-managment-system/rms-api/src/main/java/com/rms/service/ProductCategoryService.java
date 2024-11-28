package com.rms.service;

import com.rms.domain.core.ProductCategory;
import com.rms.repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;

    public Page<ProductCategory> getAll(Integer page , Integer size) {
        return productCategoryRepository.findAll(PageRequest.of(page , size));
    }

    public Optional<ProductCategory> getById(Integer id) {
        return productCategoryRepository.findById(id);
    }

    public Optional<ProductCategory>findByName(String name)
    {
        return productCategoryRepository.findByName(name);
    }

    public ProductCategory save(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    public void delete(ProductCategory category) {
        productCategoryRepository.delete(category);
    }

}
