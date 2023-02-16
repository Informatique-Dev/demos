package com.rms.rest.modelmapper;

import com.rms.domain.security.Role;
import com.rms.rest.dto.RoleDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends JPAEntityMapper<Role, RoleDto> {
}
