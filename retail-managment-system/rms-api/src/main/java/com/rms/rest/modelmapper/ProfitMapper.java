package com.rms.rest.modelmapper;

import com.rms.domain.investor.Profit;
import com.rms.rest.dto.ProfitDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfitMapper {
    ProfitMapper INSTANCE = Mappers.getMapper(ProfitMapper.class);

    ProfitDto toProfitDto(Profit profit);

    List<ProfitDto> toProfitDto(List<Profit> profits);

    Profit toProfit(ProfitDto profitDto);

    List<Profit> toProfit(List<ProfitDto> profitDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfitFromDto(ProfitDto profitDto, @MappingTarget Profit profit);
}
