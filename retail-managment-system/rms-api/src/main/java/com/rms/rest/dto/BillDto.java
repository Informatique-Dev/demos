package com.rms.rest.dto;

import com.rms.domain.purchase.Supplier;
import com.rms.rest.dto.common.GenericDto;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BillDto extends GenericDto {

    @Size(max = 70, message = "bill number max length allowed is 70 characters", groups = {InsertValidation.class, UpdateValidation.class})

    private String billNo;
    private LocalDate date;
    @Size(max = 205, message = "recipient max length allowed is 205 characters", groups = {InsertValidation.class, UpdateValidation.class})
    private String recipient;
    private String notes;
    private SupplierDto supplierDto;
}
