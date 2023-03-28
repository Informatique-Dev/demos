package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;



@Data
public class OrderItemDto extends GenericDto {
    private Double unitPrice;

   @Range(min = 1, message = "quantity's min allowed is 1 ", groups = {InsertValidation.class, UpdateValidation.class})
    private Integer quantity;
    private ProductDto product;
    private  OrderDto order;

}
