package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CustomerDto extends GenericDto {

    private String fullName;
    private String nickName;
    @Pattern(regexp = "[0-9]{14}" , message = "National Id length allowed is 14 characters and must be Numbers" , groups = {InsertValidation.class, UpdateValidation.class})
    private String nationalId;
    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number and must be numbers"  , groups = {InsertValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone number is mandatory",groups = {InsertValidation.class , UpdateValidation.class})
    private String primaryPhoneNo;
    @Pattern(regexp = "[0-9]{11}" , message = "Phone Number must be 11 number and must be numbers"  , groups = {InsertValidation.class, UpdateValidation.class})
    @NotBlank(message = "Phone number is mandatory",groups = {InsertValidation.class , UpdateValidation.class})
    private String secondaryPhoneNo;
    private String address;
    private String customerCode;
    private Integer trustReceiptNo;
}
