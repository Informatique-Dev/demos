package com.rms.rest.modelmapper;

import com.rms.domain.sales.Order;
import com.rms.rest.dto.OrderDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends JPAEntityMapper<Order ,OrderDto> {


}
