package com.rms.service;

import com.rms.domain.core.Product;
import com.rms.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> getById(Integer id) {
        return productRepository.findById(id);
    }

    public Page<Product> getAll(Integer page , Integer size) {
        return productRepository.findAll(PageRequest.of(page,size));
    }

    public List<Product> getByProductCategory(Integer catId) {
        return productRepository.getByProductCategory(catId);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

}
