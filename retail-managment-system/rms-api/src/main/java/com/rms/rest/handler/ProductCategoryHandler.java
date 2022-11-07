package com.rms.rest.handler;

import com.rms.domain.core.ProductCategory;
import com.rms.rest.dto.ProductCategoryDto;
import com.rms.rest.modelmapper.ProductCategoryMapper;
import com.rms.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductCategoryHandler {
    private ProductCategoryService productCategoryService;
    private ProductCategoryMapper mapper;

    public ResponseEntity<List<ProductCategoryDto>> getAll() {
        List<ProductCategory> productCategories = productCategoryService.getAll();
        List<ProductCategoryDto> dtos = mapper.toProductCategoryDto(productCategories);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<ProductCategoryDto> getById(Integer id) {
        ProductCategory productCategory = productCategoryService.getById(id);
        ProductCategoryDto dto = mapper.toProductCategoryDto(productCategory);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductCategoryDto> add(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = mapper.toProductCategory(productCategoryDto);
        productCategoryService.save(productCategory);
        ProductCategoryDto dto = mapper.toProductCategoryDto(productCategory);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductCategoryDto> update(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = productCategoryService.getById(productCategoryDto.getId());
        mapper.updateProductCategoryFromDto(productCategoryDto, productCategory);
        productCategoryService.save(productCategory);
        ProductCategoryDto dto = mapper.toProductCategoryDto(productCategory);
        return ResponseEntity.ok(dto);
    }
}
