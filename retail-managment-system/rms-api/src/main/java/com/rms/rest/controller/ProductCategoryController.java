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
@RequestMapping("/category")
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
    @Operation(summary = "Get All", description = "this api for get all category")
    public ResponseEntity<?> getAll(@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                    @RequestParam (value = "size" , defaultValue = "10") Integer size) {
        return productCategoryHandler.getAll(page , size);
    }

    @GetMapping("/{id}/product")
    @Operation(summary = "Get By Category Id", description = "this api for get product by category id")
    public ResponseEntity<?> getProductsByCatId(@PathVariable("id") Integer id,
                                                @RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                                @RequestParam (value = "size" , defaultValue = "10") Integer size) {
        return productHandler.getByProductCategory(id , page,size);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new product category")
    public ResponseEntity<?> save(@Valid @RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryHandler.save(productCategoryDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update product category")
    public ResponseEntity<?> update(@Valid @RequestBody ProductCategoryDto productCategoryDto, @PathVariable Integer id) {
        return productCategoryHandler.update(productCategoryDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete product category By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return productCategoryHandler.delete(id);
    }
}
