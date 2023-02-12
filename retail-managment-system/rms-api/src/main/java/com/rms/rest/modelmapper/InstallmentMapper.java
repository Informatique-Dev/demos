package com.rms.rest.modelmapper;

import com.rms.domain.sales.Installment;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderMapper.class})
public interface InstallmentMapper extends JPAEntityMapper<Installment ,InstallmentDto> {

}
