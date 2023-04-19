package com.rms.rest.modelmapper;

import com.rms.domain.investor.ProfitDetails;
import com.rms.rest.dto.ProfitDetailsDto;
import com.rms.service.ProductService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProfitDetailsMapper {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;

    @Mappings({
            @Mapping(source = "product", target = "product", ignore = true)
    })
    public abstract ProfitDetailsDto toDto(ProfitDetails profitDetails);

    public abstract List<ProfitDetailsDto> toDto(List<ProfitDetails> list);

    @AfterMapping
    public void toDtoAfterMapping(ProfitDetails entity, @MappingTarget ProfitDetailsDto dto) {
        if (HibernateUtils.isConvertable(entity.getProduct())) {
            dto.setProduct(productMapper.toDto(entity.getProduct()));
        }
    }

    @InheritInverseConfiguration
    public abstract ProfitDetails toEntity(ProfitDetailsDto profitDetailsDto);

    public abstract List<ProfitDetails> toEntity(List<ProfitDetailsDto> list);

    @AfterMapping
    public void toEntityAfterMapping(ProfitDetailsDto dto, @MappingTarget ProfitDetails entity) {
        if (dto.getProduct() != null) {
            entity.setProduct(productService.getById(dto.getProduct().getId()).get());
        }
    }

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ProfitDetails updateEntityFromDto(ProfitDetailsDto profitDetailsDto, @MappingTarget ProfitDetails profitDetails);

}
