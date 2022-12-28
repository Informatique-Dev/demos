package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

@Data
public class ProductDto extends GenericDto {

    private String name;
    private String modelNo;
    private String brand;
    private Double cashPrice;
    private Integer quantity;

    private ProductCategoryDto productCategoryDto;
}
