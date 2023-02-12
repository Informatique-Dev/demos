package com.rms.rest.modelmapper;

import com.rms.domain.sales.Customer;
import com.rms.rest.dto.CustomerDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper extends JPAEntityMapper<Customer , CustomerDto> {


}
