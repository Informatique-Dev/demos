package com.rms.rest.modelmapper;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto toTransactionDto(Transaction transaction);

    List<TransactionDto> toTransactionDto(List<Transaction> transactions);

    Transaction toTransaction(TransactionDto transactionDto);

    List<Transaction> toTransaction(List<TransactionDto> transactionDtos);

    void updateTransactionFromDto(TransactionDto transactionDto, @MappingTarget Transaction transaction);
}
