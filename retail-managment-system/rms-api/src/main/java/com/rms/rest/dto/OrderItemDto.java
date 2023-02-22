package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;


@Data
public class OrderItemDto extends GenericDto {

    private Double unitPrice;
    private Integer quantity;
    private Short paymentType;
    private ProductDto product;
    private  OrderDto order;

}
