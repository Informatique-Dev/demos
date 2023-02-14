package com.rms.rest.modelmapper;

import com.rms.domain.sales.Installment;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import com.rms.service.OrderService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class InstallmentMapper {
    @Autowired
    private OrderMapper orderMapper ;
    @Autowired
    private OrderService orderService ;

    @Mapping(source = "order", target = "order", ignore = true)
    public abstract  InstallmentDto toDto(Installment installment);

    public abstract List<InstallmentDto> toDto(List<Installment> list);

    @AfterMapping
    public void toDtoAfterMapping(Installment entity, @MappingTarget InstallmentDto dto){
        if (HibernateUtils.isConvertable(entity.getOrder())) {
            dto.setOrder(orderMapper.toDto(entity.getOrder()));
        }
    }

    @InheritInverseConfiguration
    public abstract Installment toEntity(InstallmentDto installmentDto);

    public abstract List<Installment> toEntity(List<InstallmentDto> list);

    @AfterMapping
    public void toEntityAfterMapping(InstallmentDto dto, @MappingTarget Installment entity) {
        if (dto.getOrder() != null) {
            entity.setOrder(orderService.getById(dto.getOrder().getId()).get());
        }
    }

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Installment updateEntityFromDto(InstallmentDto installmentDto, @MappingTarget Installment installment);

}
