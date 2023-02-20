package com.rms.domain.security;

import com.rms.domain.common.LookupEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "roles")
public class Role extends LookupEntity {

}
