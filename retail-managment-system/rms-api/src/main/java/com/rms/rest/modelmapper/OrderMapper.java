package com.rms.rest.modelmapper;

import com.rms.domain.sales.Order;
import com.rms.rest.dto.OrderDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    //@Mapping(source = "customer.id", target = "customerId")
    OrderDto toOrderDto(Order order);

    List<OrderDto> toOrderDto(List<Order> orders);

    @InheritInverseConfiguration
    Order toOrder(OrderDto orderDto);

    List<Order> toOrder(List<OrderDto> orderDtos);

    void updateOrderFromDto(OrderDto orderItemDto, @MappingTarget Order orderItem);

}
