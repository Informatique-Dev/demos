package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.core.Product;
import com.rms.domain.core.ProductCategory;
import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.ProductMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.ProductCategoryService;
import com.rms.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductHandler {
    private ProductService productService;
    private ProductMapper mapper;
    private PaginationMapper paginationMapper ;

    private ProductCategoryService productCategoryService;

    public ResponseEntity<?> getById(Integer id) {
        Product product = productService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
        ProductDto dto = mapper.toDto(product);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> getAll(Integer page , Integer size)
    {
        Page<Product> products = productService.getAll(page, size);
        List<ProductDto> dtos =mapper.toDto(products.getContent());
        PaginatedResultDto<ProductDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(products));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getByProductCategory(Integer catId) {
        List<Product> products = productService.getByProductCategory(catId);
        List<ProductDto> dtos = mapper.toDto(products);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<ProductDto> save(ProductDto productDto) {
        ProductCategory productCategory = productCategoryService.getById(productDto.getProductCategoryDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(ProductCategory.class.getSimpleName(), productDto.getProductCategoryDto().getId()));
        Product product = mapper.toEntity(productDto);
        product.setProductCategory(productCategory);
        productService.save(product);
        ProductDto dto = mapper.toDto(product);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductDto> update(ProductDto productDto, Integer id) {
        Product product = productService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(), id));
        if(productDto.getProductCategoryDto()!=null) {
            ProductCategory productCategory = productCategoryService.getById(productDto.getProductCategoryDto().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(ProductCategory.class.getSimpleName(), productDto.getProductCategoryDto().getId()));
            product.setProductCategory(productCategory);
        }
        mapper.updateEntityFromDto(productDto, product);
        productService.save(product);
        ProductDto dto = mapper.toDto(product);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
      Product product=  productService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(),id));
        try {
            productService.delete(product);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Product.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
