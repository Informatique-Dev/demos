package com.rms.rest.modelmapper;

import com.rms.domain.purchase.Supplier;
import com.rms.rest.dto.SupplierDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.Mapper;

import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel ="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupplierMapper extends JPAEntityMapper<Supplier, SupplierDto> {
}
