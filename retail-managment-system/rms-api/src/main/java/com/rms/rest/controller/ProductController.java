package com.rms.rest.controller;

import com.rms.rest.dto.ProductDto;
import com.rms.rest.handler.ProductHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductHandler productHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return productHandler.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return productHandler.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProductDto product) {
        return productHandler.add(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDto product, @PathVariable Integer id) {
        return productHandler.update(product);
    }
}
