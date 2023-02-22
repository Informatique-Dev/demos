package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class OrderItemDto extends GenericDto {
    @NotBlank(message = "Price is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(min = 1, message = "Price's min allowed is 1 ", groups = {InsertValidation.class, UpdateValidation.class})

    private Double unitPrice;
    @NotBlank(message = "quantity is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(min = 1, message = "quantity's min allowed is 1 ", groups = {InsertValidation.class, UpdateValidation.class})
    private Integer quantity;
    private Short paymentType;
    private ProductDto product;
    private  OrderDto order;

}
