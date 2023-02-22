package com.rms.rest.modelmapper;

import com.rms.domain.investor.Transaction;
import com.rms.rest.dto.TransactionDto;
import com.rms.service.InvestorService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TransactionMapper{
    @Autowired
    private InvestorMapper investorMapper ;
     @Autowired
    private InvestorService investorService ;



    @Mapping(source = "investor" , target = "investor" , ignore = true)
    public abstract TransactionDto toDto(Transaction transaction);


    public abstract List<TransactionDto> toDto (List<Transaction> list);

    @AfterMapping
    public void toDtoAfterMapping(Transaction entity, @MappingTarget TransactionDto dto){

        if (HibernateUtils.isConvertable(entity.getInvestor())) {
            dto.setInvestor(investorMapper.toDto(entity.getInvestor()));
        }
    }


    @InheritInverseConfiguration
    public abstract Transaction toEntity(TransactionDto dto);

    public abstract List<Transaction> toEntity(List<TransactionDto> list);

    @AfterMapping
    public void toEntityAfterMapping(TransactionDto dto, @MappingTarget Transaction entity) {

        if (dto.getInvestor() != null) {
            entity.setInvestor(investorService.getById(dto.getInvestor().getId()).get());
        }
    }
    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  Transaction updateEntityFromDto(TransactionDto dto, @MappingTarget Transaction entity);

}
