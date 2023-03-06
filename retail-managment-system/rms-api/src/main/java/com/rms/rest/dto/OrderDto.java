package com.rms.rest.dto;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.PaymentType;
import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto extends GenericDto {

    private Date orderDate;
    private Double paidAmount;
    private Double remainingAmount;
    private Double installmentAmount;
    private Double totalPrice;

    private PaymentType paymentType;

    private EmployeeDto employee;
    private CustomerDto customer;
    private List<OrderItemDto> orderItems;
    private List<InstallmentDto> installments;

}
