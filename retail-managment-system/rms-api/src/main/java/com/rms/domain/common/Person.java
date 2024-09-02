package com.rms.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Person extends AuditingEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "national_id")
    private String nationalId;

    @Column(name = "primary_phone_no")
    private String primaryPhoneNo;

    @Column(name = "secondary_phone_no")
    private String secondaryPhoneNo;

    @Column(name = "address")
    private String address;

    // test webhook
}
