package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EmployeeDto extends GenericDto {

    private String fullName;

    private String nickName;

    @Pattern(regexp = "[0-9]{14}" , message = "National Id must be 14 number")
    @NotNull(message = "National ID is required ")
    private String nationalId;

    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number")
    @NotNull(message = "Phone Number is required")
    private String primaryPhoneNo;

    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number")
    private String secondaryPhoneNo;

    private String address;

    private String job;

}
