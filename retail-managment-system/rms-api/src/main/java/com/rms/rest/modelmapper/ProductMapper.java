package com.rms.rest.modelmapper;

import com.rms.domain.core.Product;
import com.rms.domain.core.ProductCategory;
import com.rms.rest.dto.ProductDto;
import com.rms.service.ProductCategoryService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE/*, uses = {ProductCategoryMapper.class}*/)
public abstract class ProductMapper {
//    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductCategoryService productCategoryService ;

    @Mapping(source = "productCategory", target = "productCategoryDto")
    public  abstract  ProductDto toProductDto(Product product);

    public  abstract List<ProductDto> toProductDto(List<Product> products);

    @AfterMapping
    public void toDtoAfterMapping(Product entity, @MappingTarget ProductDto dto){
        if (HibernateUtils.isConvertable(entity.getProductCategory())) {
            dto.setProductCategoryDto(productCategoryMapper.toProductCategoryDto(entity.getProductCategory()));
        }
    }


    @InheritInverseConfiguration
    public  abstract Product toProduct(ProductDto productDto);

    public  abstract List<Product> toProduct(List<ProductDto> productDtos);


    @AfterMapping
    public void toEntityAfterMapping(ProductDto dto, @MappingTarget Product entity) {
        if (dto.getProductCategoryDto() != null) {
            entity.setProductCategory(productCategoryService.getById(dto.getProductCategoryDto().getId()).get());
        }

    }


    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract Product updateProductFromDto(ProductDto productDto, @MappingTarget Product product);
}
