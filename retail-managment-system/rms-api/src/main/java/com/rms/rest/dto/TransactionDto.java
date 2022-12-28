package com.rms.rest.dto;

import com.rms.domain.investor.TransactionType;
import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDto extends GenericDto {

    private TransactionType transactionType;
    private Double amount;
    private LocalDate date;

    private InvestorDto investorDto;


}
