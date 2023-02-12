package com.rms.rest.modelmapper;

import com.rms.domain.core.Product;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE/*, uses = {ProductCategoryMapper.class}*/)
public interface ProductMapper extends JPAEntityMapper<Product , ProductDto> {
}
