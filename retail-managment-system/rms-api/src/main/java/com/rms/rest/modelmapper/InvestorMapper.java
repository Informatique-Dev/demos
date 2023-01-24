package com.rms.rest.modelmapper;

import com.rms.domain.investor.Investor;
import com.rms.rest.dto.InvestorDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvestorMapper {
    InvestorMapper INSTANCE = Mappers.getMapper(InvestorMapper.class);

    InvestorDto toInvestorDto(Investor investor);

    List<InvestorDto> toInvestorDto(List<Investor> investors);

    Investor toInvestor(InvestorDto investorDto);

    List<Investor> toInvestor(List<InvestorDto> investorDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateInvestorFromDto(InvestorDto investorDto, @MappingTarget Investor investor);
}
