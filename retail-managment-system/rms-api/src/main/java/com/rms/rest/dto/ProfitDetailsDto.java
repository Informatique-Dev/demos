package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;


@Data
public class ProfitDetailsDto extends GenericDto {
    private double billPrice;
    private double productPrice;
    private int count;
    private ProductDto product;
}
