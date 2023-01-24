package com.rms.rest.controller;

import com.rms.rest.dto.ProductCategoryDto;
import com.rms.rest.handler.ProductCategoryHandler;
import com.rms.rest.handler.ProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Tag(name = "Product Category", description = "Rest Api For Product Category")
public class ProductCategoryController {
    private ProductCategoryHandler productCategoryHandler;
    private ProductHandler productHandler;

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get product category by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return productCategoryHandler.getById(id);
    }

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all product categories")
    public ResponseEntity<?> getAll() {
        return productCategoryHandler.getAll();
    }

    @GetMapping("/{id}/products")
    @Operation(summary = "Get By Category Id", description = "this api for get product by category id")
    public ResponseEntity<?> getProductsByCatId(@PathVariable("id") Integer id) {
        return productHandler.getByProductCategory(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new product category")
    public ResponseEntity<?> add(@Valid @RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryHandler.add(productCategoryDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update product category")
    public ResponseEntity<?> update(@Valid @RequestBody ProductCategoryDto productCategoryDto, @PathVariable Integer id) {
        return productCategoryHandler.update(productCategoryDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product category By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return productCategoryHandler.deleteById(id);
    }
}
