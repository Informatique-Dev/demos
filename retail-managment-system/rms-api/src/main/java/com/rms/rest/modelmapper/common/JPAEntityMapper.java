package com.rms.rest.modelmapper.common;

import com.rms.domain.common.AuditingEntity;
import com.rms.domain.common.JPAEntity;
import com.rms.rest.dto.common.GenericDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface JPAEntityMapper <T extends JPAEntity , S extends GenericDto>
{
    T toEntity(S s);

    S toDto(T t);

    List<T> toEntity(List<S> dtoList);

    List<S> toDto(List<T> dtoList);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

    T updateEntityFromDto(S s, @MappingTarget T t);
}
