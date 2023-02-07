package com.rms.rest.modelmapper;

import com.rms.domain.sales.OrderItem;
import com.rms.rest.dto.OrderItemDto;
import com.rms.service.OrderService;
import com.rms.service.ProductService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProductMapper.class})
public abstract class OrderItemMapper {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductService productService;

    @Autowired
            private OrderService orderService ;
    @Autowired
    private OrderMapper orderMapper ;

  //  OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mappings({
    @Mapping(source = "product" , target = "product" , ignore = true) ,
    @Mapping(source = "order" , target = "order" , ignore = true)
    })
    public abstract OrderItemDto toOrderItemDto(OrderItem orderItem);

    public abstract List<OrderItemDto> toOrderItemDto(List<OrderItem> orderItems);


    @AfterMapping
    public void toDtoAfterMapping(OrderItem entity, @MappingTarget OrderItemDto dto) {
        if (HibernateUtils.isConvertable(entity.getProduct())) {
            dto.setProduct(productMapper.toProductDto(entity.getProduct()));
        }
        if (HibernateUtils.isConvertable(entity.getOrder())) {
            dto.setOrder(orderMapper.toOrderDto(entity.getOrder()));
        }
    }


        @InheritInverseConfiguration
        public abstract OrderItem toOrderItem (OrderItemDto orderItemDto);

        public abstract List<OrderItem> toOrderItem (List < OrderItemDto > orderItemDtos);


    @AfterMapping
    public void toEntityAfterMapping(OrderItemDto dto, @MappingTarget OrderItem entity) {
        if (dto.getProduct() != null) {
            entity.setProduct(productService.getById(dto.getProduct().getId()).get());
        }
        if (dto.getOrder() != null) {
            entity.setOrder(orderService.getById(dto.getOrder().getId()).get());
        }
    }


    @InheritInverseConfiguration
        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        public abstract OrderItem updateOrderItemFromDto (OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem)
        ;
    }
