package com.rms.rest.modelmapper;

import com.rms.domain.core.ProductCategory;
import com.rms.rest.dto.ProductCategoryDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper extends JPAEntityMapper<ProductCategory , ProductCategoryDto> {

}
