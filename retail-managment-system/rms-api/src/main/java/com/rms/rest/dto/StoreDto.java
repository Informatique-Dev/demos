package com.rms.rest.dto;

import com.rms.domain.core.Employee;
import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StoreDto extends GenericDto {

    @Size(max = 100, message = "Name's max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String name;

    @NotBlank(message = "Address is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max = 100, message = "Address's max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String address;
    private EmployeeDto responsible;
}
