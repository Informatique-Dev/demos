package com.rms.rest.modelmapper;

import com.rms.domain.investor.Investor;
import com.rms.rest.dto.InvestorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvestorMapper {
    InvestorMapper INSTANCE = Mappers.getMapper(InvestorMapper.class);

    InvestorDto toInvestorDto(Investor investor);

    List<InvestorDto> toInvestorDto(List<Investor> investors);

    Investor toInvestor(InvestorDto investorDto);

    List<Investor> toInvestor(List<InvestorDto> investorDtos);

    void updateInvestorFromDto(InvestorDto investorDto, @MappingTarget Investor investor);
}
