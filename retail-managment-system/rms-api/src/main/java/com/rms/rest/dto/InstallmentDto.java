package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import java.util.Date;


@Data
public class InstallmentDto extends GenericDto {

    private Double installmentAmount;
    private Double paymentAmount;
    private Date dueDate;
    private Date paymentDate;
    private Short status;
    private OrderDto order;
}
