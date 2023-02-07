package com.rms.rest.modelmapper;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper implements JPAEntityMapper<Transaction ,TransactionDto> {

}
