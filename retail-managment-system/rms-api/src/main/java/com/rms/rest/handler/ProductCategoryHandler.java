package com.rms.rest.handler;

import com.rms.domain.core.ProductCategory;
import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.ProductCategoryDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.ProductCategoryMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductCategoryHandler {
    private ProductCategoryService productCategoryService;
    private ProductCategoryMapper mapper;
    private PaginationMapper paginationMapper ;

    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<ProductCategory> productCategories = productCategoryService.getAll(page, size);
        List<ProductCategoryDto> dtos = mapper.toDto(productCategories.getContent());
        PaginatedResultDto<ProductCategoryDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(productCategories));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<ProductCategoryDto> getById(Integer id) {
        ProductCategory productCategory = productCategoryService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id));
        ProductCategoryDto dto = mapper.toDto(productCategory);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductCategoryDto> save(ProductCategoryDto productCategoryDto) {
        Optional<ProductCategory>productCategoryexist=productCategoryService.findByName(productCategoryDto.getName());

        if(productCategoryexist.isPresent())
        {

            throw new ResourceAlreadyExistsException(ProductCategory.class.getSimpleName(),
                    "product category name",productCategoryDto.getName(),ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        ProductCategory productCategory = mapper.toEntity(productCategoryDto);
        productCategoryService.save(productCategory);
        ProductCategoryDto dto = mapper.toDto(productCategory);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<ProductCategoryDto> update(ProductCategoryDto productCategoryDto, Integer id) {
        Optional<ProductCategory>productCategoryexist=productCategoryService.findByName(productCategoryDto.getName());
        if(productCategoryexist.isPresent()&&!productCategoryexist.get().getId().equals(id))
        {
            throw new ResourceAlreadyExistsException(ProductCategory.class.getSimpleName(),
                    "product category name",productCategoryDto.getName(),ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        ProductCategory productCategory = productCategoryService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id));
        mapper.updateEntityFromDto(productCategoryDto, productCategory);
        productCategory.setId(id);
        productCategoryService.save(productCategory);
        ProductCategoryDto dto = mapper.toDto(productCategory);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
       ProductCategory category = productCategoryService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ProductCategory.class.getSimpleName(), id));
        try {
            productCategoryService.delete(category);
        } catch (Exception exception) {
            throw new ResourceRelatedException(ProductCategory.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
