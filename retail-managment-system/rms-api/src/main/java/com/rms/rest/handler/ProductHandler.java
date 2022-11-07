package com.rms.rest.handler;

import com.rms.domain.core.Product;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.modelmapper.ProductMapper;
import com.rms.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductHandler {
    private ProductService productService;
    private ProductMapper mapper;

    public ResponseEntity<ProductDto> getById(Integer id) {
        Product product = productService.getById(id);
        ProductDto dto = mapper.toProductDto(product);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> products = productService.getAll();
        List<ProductDto> dtos = mapper.toProductDto(products);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<ProductDto>> getByProductCategory(Integer catId) {
        List<Product> products = productService.getByProductCategory(catId);
        List<ProductDto> dtos = mapper.toProductDto(products);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<ProductDto> add(ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        productService.save(product);
        ProductDto dto = mapper.toProductDto(product);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductDto> update(ProductDto productDto) {
        Product product = productService.getById(productDto.getId());
        mapper.updateProductFromDto(productDto, product);
        productService.save(product);
        ProductDto dto = mapper.toProductDto(product);
        return ResponseEntity.ok(dto);
    }
}
