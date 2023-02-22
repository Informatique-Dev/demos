package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SupplierDto extends GenericDto {

    @NotBlank(message = "Name is required" , groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max=205 ,message = "Name's max length allowed is 205 characters" ,groups = {InsertValidation.class, UpdateValidation.class})
    private String name;
    @NotBlank(message = "Contact Name is required" , groups = {InsertValidation.class, UpdateValidation.class})
    @Size(max=205 ,message = "Contact Name's max length allowed is 205 characters" ,groups = {InsertValidation.class, UpdateValidation.class})
    private String contactName;

    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number and must be numbers"  , groups = {InsertValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone number is mandatory",groups = {InsertValidation.class , UpdateValidation.class})
    private String primaryPhoneNo;
    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number and must be numbers"  , groups = {InsertValidation.class, UpdateValidation.class})
    private String secondaryPhoneNo;

    @Size(max = 60, message = "Address's max length allowed is 60 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String address;
}
