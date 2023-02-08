package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EmployeeDto extends GenericDto {

    @NotBlank(message = "Full Name is mandatory", groups = {InsertValidation.class})
    @Size(max = 205, message = "Name's max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String fullName;

    @Size(max = 60, message = "Nick Name's max length allowed is 60 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String nickName;

    @NotBlank(message = "National id is mandatory",groups = {InsertValidation.class , UpdateValidation.class})
    @Size(min=14, max = 14, message = "National Id length allowed is 14 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String nationalId;

   // @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number")
   @Size(min=11, max = 11, message = "Phone Number length allowed is 11 characters", groups = {InsertValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone number is mandatory",groups = {InsertValidation.class})
    private String primaryPhoneNo;

    //@Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number")
    @Size(min=11, max = 11, message = "Phone Number length allowed is 11 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String secondaryPhoneNo;

    @Size(max = 60, message = "Address's max length allowed is 60 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String address;

    @Size(max = 60, message = "Job's max length allowed is 60 characters", groups = {InsertValidation.class, UpdateValidation.class})

    private String job;

}
