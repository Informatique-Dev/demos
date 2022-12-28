package com.rms.rest.dto;

import com.rms.domain.investor.InvestorType;
import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

import java.time.LocalDate;


@Data
public class InvestorDto extends GenericDto {

    private String fullName;
    private String nickName;
    private String nationalId;
    private String primaryPhoneNo;
    private String secondaryPhoneNo;
    private String address;
    private InvestorType investorType;
    private Double balance;
    private LocalDate startDate;

}
