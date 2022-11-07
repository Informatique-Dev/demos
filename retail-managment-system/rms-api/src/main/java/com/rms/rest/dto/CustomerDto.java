package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

@Data
public class CustomerDto extends GenericDto {

    private String fullName;
    private String customerCode;
    private String nickName;
    private String nationalId;
    private String primaryPhoneNo;
    private String secondaryPhoneNo;
    private String address;
    private Integer trustReceiptNo;
}
