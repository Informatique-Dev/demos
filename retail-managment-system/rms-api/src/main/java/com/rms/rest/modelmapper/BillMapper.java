package com.rms.rest.modelmapper;

import com.rms.domain.core.Product;
import com.rms.domain.purchase.Bill;
import com.rms.rest.dto.BillDto;
import com.rms.rest.dto.ProductDto;
import com.rms.service.SupplierService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.transform.Source;
import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class BillMapper {

    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierService supplierService;

    @Mapping(source="supplier",target = "supplierDto")
    public abstract BillDto toDto(Bill bill);
    public abstract List<BillDto> toDto(List<Bill> bills);

    @AfterMapping
    public  void toDtoAfterMapping(Bill bill,@MappingTarget BillDto billDto)
    {
        if (HibernateUtils.isConvertable(bill.getSupplier()))
        {
           billDto.setSupplierDto(supplierMapper.toDto(bill.getSupplier()));
        }
    }

    @InheritInverseConfiguration
    public  abstract Bill toEntity(BillDto billDto);

    public  abstract List<Bill> toEntity(List<BillDto> list);
    @AfterMapping
    public void toEntityAfterMapping (BillDto billDto,@MappingTarget Bill bill)
    {
        if(billDto.getSupplierDto()!=null)
        {
            bill.setSupplier(supplierService.getById(billDto.getSupplierDto().getId()).get());
        }
    }



    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public  abstract Bill updateEntityFromDto(BillDto billDto, @MappingTarget Bill bill);



}
