package com.rms.rest.modelmapper;

import com.rms.domain.sales.OrderItem;
import com.rms.rest.dto.OrderItemDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class})
public interface OrderItemMapper extends JPAEntityMapper<OrderItem , OrderItemDto> {

}
