package com.rms.rest.modelmapper;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper {
//    public abstract TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    public abstract TransactionDto toTransactionDto(Transaction transaction);

    public abstract List<TransactionDto> toTransactionDto(List<Transaction> transactions);

    @InheritInverseConfiguration
    public abstract Transaction toTransaction(TransactionDto transactionDto);

    public abstract List<Transaction> toTransaction(List<TransactionDto> transactionDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateTransactionFromDto(TransactionDto transactionDto, @MappingTarget Transaction transaction);
}
