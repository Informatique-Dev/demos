package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ProfitDto extends GenericDto {
    private String bookNo;
    private Double profitAmount;
    private LocalDate date;
    private boolean calculated;


}
