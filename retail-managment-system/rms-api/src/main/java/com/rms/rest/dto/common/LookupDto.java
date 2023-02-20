package com.rms.rest.dto.common;

import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LookupDto extends GenericDto {
    @NotBlank(message = "Arabic Name is mandatory", groups = {InsertValidation.class})
    @Size(max = 50, message = "Arabic name's max length allowed is 50 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String arabicName;

    @Size(max = 50, message = "English name's max length allowed is 50 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String englishName;

    @Size(max = 50, message = "Code's max length allowed is 50 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String code;

    private Boolean enabled;
}
