package com.rms.rest.modelmapper;

import com.rms.domain.investor.Profit;
import com.rms.rest.dto.ProfitDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfitMapper extends JPAEntityMapper<Profit , ProfitDto> {

}
