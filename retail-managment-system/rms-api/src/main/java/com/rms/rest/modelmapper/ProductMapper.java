package com.rms.rest.modelmapper;

import com.rms.domain.core.Product;
import com.rms.rest.dto.ProductDto;
import com.rms.service.ProductCategoryService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductMapper  {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductCategoryService productCategoryService ;

    @Mapping(source = "productCategory", target = "productCategoryDto")
    public  abstract  ProductDto toDto(Product product);

    public  abstract List<ProductDto> toDto(List<Product> list);

    @AfterMapping
    public void toDtoAfterMapping(Product entity, @MappingTarget ProductDto dto){
        if (HibernateUtils.isConvertable(entity.getProductCategory())) {
            dto.setProductCategoryDto(productCategoryMapper.toDto(entity.getProductCategory()));
        }
    }


    @InheritInverseConfiguration
    public  abstract Product toEntity(ProductDto productDto);

    public  abstract List<Product> toEntity(List<ProductDto> list);


    @AfterMapping
    public void toEntityAfterMapping(ProductDto dto, @MappingTarget Product entity) {
        if (dto.getProductCategoryDto() != null) {
            entity.setProductCategory(productCategoryService.getById(dto.getProductCategoryDto().getId()).get());
        }

    }


    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract Product updateEntityFromDto(ProductDto productDto, @MappingTarget Product product);
}
