package com.rms.rest.controller;

import com.rms.rest.dto.ProductCategoryDto;
import com.rms.rest.handler.ProductCategoryHandler;
import com.rms.rest.handler.ProductHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class ProductCategoryController {
    private ProductCategoryHandler productCategoryHandler;
    private ProductHandler productHandler;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return productCategoryHandler.getById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return productCategoryHandler.getAll();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<?> getProductsByCatId(@PathVariable("id") Integer id) {
        return productHandler.getByProductCategory(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryHandler.add(productCategoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ProductCategoryDto productCategoryDto, @PathVariable Integer id) {
        return productCategoryHandler.update(productCategoryDto);
    }
}
