package com.rms.rest.modelmapper;

import com.rms.domain.core.Product;
import com.rms.rest.dto.ProductDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE/*, uses = {ProductCategoryMapper.class}*/)
public interface ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "productCategory", target = "productCategoryDto")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDto(List<Product> products);

    @InheritInverseConfiguration
    Product toProduct(ProductDto productDto);

    List<Product> toProduct(List<ProductDto> productDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
