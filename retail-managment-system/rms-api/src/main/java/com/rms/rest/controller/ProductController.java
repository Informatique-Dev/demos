package com.rms.rest.controller;

import com.rms.rest.dto.ProductDto;
import com.rms.rest.handler.ProductHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@Tag(name = "Product", description = "Rest Api For Product")
public class ProductController {
    private ProductHandler productHandler;



    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all product")
    public ResponseEntity<?> getAll(@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                    @RequestParam (value = "size" , defaultValue = "10") Integer size) {
        return productHandler.getAll(page,size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get product by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return productHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add product")
    public ResponseEntity<?> save(@RequestBody ProductDto product) {
        return productHandler.save(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update product")
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable Integer id) {
        return productHandler.update(productDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return productHandler.delete(id);
    }
}
