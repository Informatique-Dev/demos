package com.rms.rest.modelmapper;

import com.rms.domain.sales.Installment;
import com.rms.rest.dto.InstallmentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderMapper.class})
public interface InstallmentMapper {
    InstallmentMapper INSTANCE = Mappers.getMapper(InstallmentMapper.class);

    InstallmentDto toInstallmentDto(Installment installment);

    List<InstallmentDto> toInstallmentDto(List<Installment> installments);

    @InheritInverseConfiguration
    Installment toInstallment(InstallmentDto installmentDto);

    List<Installment> toInstallment(List<InstallmentDto> installmentDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInstallmentFromDto(InstallmentDto installmentDto, @MappingTarget Installment installment);
}
