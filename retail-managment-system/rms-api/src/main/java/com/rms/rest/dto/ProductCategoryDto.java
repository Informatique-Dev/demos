package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductCategoryDto extends GenericDto {

    @NotEmpty(message = "Name is mandatory!")
    private String name;

    @NotNull(message = "Status is mandatory!")
    private Boolean status;

}
