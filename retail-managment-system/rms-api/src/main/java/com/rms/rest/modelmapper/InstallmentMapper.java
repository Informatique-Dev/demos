package com.rms.rest.modelmapper;

import com.rms.domain.sales.Installment;
import com.rms.domain.sales.Order;
import com.rms.rest.dto.InstallmentDto;
import com.rms.service.OrderService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderMapper.class})
public abstract class InstallmentMapper {
    //InstallmentMapper INSTANCE = Mappers.getMapper(InstallmentMapper.class);

    @Autowired
    private OrderMapper orderMapper ;
    @Autowired
    private OrderService orderService ;

    @Mapping(source = "order", target = "order", ignore = true)
    public abstract  InstallmentDto toInstallmentDto(Installment installment);

    public abstract List<InstallmentDto> toInstallmentDto(List<Installment> installments);

    @AfterMapping
    public void toDtoAfterMapping(Installment entity, @MappingTarget InstallmentDto dto){
        if (HibernateUtils.isConvertable(entity.getOrder())) {
            dto.setOrder(orderMapper.toOrderDto(entity.getOrder()));
        }
    }

    @InheritInverseConfiguration
    public abstract Installment toInstallment(InstallmentDto installmentDto);

    public abstract List<Installment> toInstallment(List<InstallmentDto> installmentDtos);

    @AfterMapping
    public void toEntityAfterMapping(InstallmentDto dto, @MappingTarget Installment entity) {
        if (dto.getOrder() != null) {
            entity.setOrder(orderService.getById(dto.getOrder().getId()).get());
        }
    }

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Installment updateInstallmentFromDto(InstallmentDto installmentDto, @MappingTarget Installment installment);
}
