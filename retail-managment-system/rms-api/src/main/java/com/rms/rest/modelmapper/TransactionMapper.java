package com.rms.rest.modelmapper;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.dto.TransactionDto;
import com.rms.service.InvestorService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper {
//    public abstract TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);


    @Autowired
    private  InvestorMapper investorMapper ;

    @Autowired
    private InvestorService investorService ;

    @Mapping(source = "investor", target = "investor", ignore = true)

    public abstract TransactionDto toTransactionDto(Transaction transaction);

    public abstract List<TransactionDto> toTransactionDto(List<Transaction> transactions);

    @AfterMapping
    public void toDtoAfterMapping(Transaction entity, @MappingTarget TransactionDto dto){
        if (HibernateUtils.isConvertable(entity.getInvestor())) {
            dto.setInvestor(investorMapper.toInvestorDto(entity.getInvestor()));
        }

    }




    @InheritInverseConfiguration
    public abstract Transaction toTransaction(TransactionDto transactionDto);

    public abstract List<Transaction> toTransaction(List<TransactionDto> transactionDtos);

    @AfterMapping
    public void toEntityAfterMapping(TransactionDto dto, @MappingTarget Transaction entity) {
        if (dto.getInvestor() != null) {
            entity.setInvestor(investorService.getById(dto.getInvestor().getId()).get());
        }

    }



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Transaction updateTransactionFromDto(TransactionDto transactionDto, @MappingTarget Transaction transaction);
}
