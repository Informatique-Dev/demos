package com.rms.rest.modelmapper;

import com.rms.domain.core.ProductCategory;
import com.rms.rest.dto.ProductCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {
    ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    ProductCategoryDto toProductCategoryDto(ProductCategory productCategory);

    List<ProductCategoryDto> toProductCategoryDto(List<ProductCategory> productCategories);

    ProductCategory toProductCategory(ProductCategoryDto productCategoryDto);

    List<ProductCategory> toProductCategory(List<ProductCategoryDto> productCategoryDtos);

    void updateProductCategoryFromDto(ProductCategoryDto productCategoryDto, @MappingTarget ProductCategory productCategory);
}
