package com.rms.rest.modelmapper;


import com.rms.domain.core.Product;
import com.rms.domain.core.Store;
import com.rms.domain.sales.Order;
import com.rms.rest.dto.OrderDto;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.dto.StoreDto;
import com.rms.service.EmployeeService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class StoreMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService ;

    @Mapping(source = "responsible", target = "responsible")
    public abstract StoreDto toDto(Store store);

    public abstract List<StoreDto> toDto(List<Store> list);

    @AfterMapping
    public void toDtoAfterMapping(Store entity, @MappingTarget StoreDto dto){

        if (HibernateUtils.isConvertable(entity.getResponsible())) {
            dto.setResponsible(employeeMapper.toDto(entity.getResponsible()));
        }
    }

    @InheritInverseConfiguration
    public abstract Store toEntity(StoreDto storeDto);

    public abstract List<Store> toEntity(List<StoreDto> storeDtos);

    @AfterMapping
    public void toEntityAfterMapping(StoreDto dto, @MappingTarget Store entity) {

        if (dto.getResponsible() != null) {
            entity.setResponsible(employeeService.getById(dto.getResponsible().getId()).get());
        }
    }
    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  Store updateEntityFromDto(StoreDto storeDto, @MappingTarget Store store);
}
