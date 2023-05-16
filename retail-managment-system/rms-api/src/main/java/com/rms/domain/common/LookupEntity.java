package com.rms.domain.common;

import liquibase.pro.packaged.S;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class LookupEntity extends  AuditingEntity{
    private String arabicName;
    private String englishName;

    private String code;

    private boolean enabled;
    
    // test jenkins V2

}
