package com.rms.rest.controller;

import com.rms.rest.dto.ProductDto;
import com.rms.rest.handler.ProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Tag(name = "Product", description = "Rest Api For Product")
public class ProductController {
    private ProductHandler productHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all products")
    public ResponseEntity<?> getAll() {
        return productHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get product by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return productHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add products")
    public ResponseEntity<?> add(@RequestBody ProductDto product) {
        return productHandler.add(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update products")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        return productHandler.update(productDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return productHandler.deleteById(id);
    }
}
