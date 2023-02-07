package com.rms.rest.modelmapper;

import com.rms.domain.investor.Investor;
import com.rms.rest.dto.InvestorDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvestorMapper extends JPAEntityMapper<Investor ,InvestorDto> {

}
