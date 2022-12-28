package com.rms.service;

import com.rms.domain.core.Product;
import com.rms.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product getById(Integer id) {
        return productRepository.getOne(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public List<Product> getByProductCategory(Integer catId) {
        return productRepository.getByProductCategory(catId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

}
