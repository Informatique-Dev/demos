package com.rms.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class UserDto extends GenericDto {


    @NotBlank(message = "User Name is mandatory" , groups = {InsertValidation.class})
    @Size(max = 16 , message = "ser Name's max length allowed is 16 characters" , groups = {InsertValidation.class , UpdateValidation.class})
    private String userName;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[#$@!%&*?]).{5,30}",
            groups = {InsertValidation.class , UpdateValidation.class},
            message ="Password must contain only letters , numbers and  special characters" )
    private String password;

    @NotNull(message = "enabled is mandatory", groups = {InsertValidation.class, UpdateValidation.class})
    private Boolean enabled;
    private EmployeeDto employee ;


    @JsonIgnore
    @JsonProperty(value = "user_password")
    public String getPassword() {
        return password;
    }
}
