package com.rms.rest.modelmapper;

import com.rms.domain.sales.Order;
import com.rms.rest.dto.OrderDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import com.rms.service.CustomerService;
import com.rms.service.EmployeeService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderMapper  {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService ;

    @Autowired
    private CustomerMapper customerMapper ;
    @Autowired
    private CustomerService customerService ;

    @Mappings({
            @Mapping(source = "employee", target = "employee", ignore = true),
            @Mapping(source = "customer", target = "customer", ignore = true)
        //    @Mapping(source = "orderItems" , target = "orderItems" , ignore = true)
      //  @Mapping(source = "installments" , target = "installments" )
            })
    public abstract  OrderDto toDto(Order order);

    public abstract  List<OrderDto> toDto(List<Order> list);

    @AfterMapping
    public void toDtoAfterMapping(Order entity, @MappingTarget OrderDto dto){
        if (HibernateUtils.isConvertable(entity.getCustomer())) {
            dto.setCustomer(customerMapper.toDto(entity.getCustomer()));
        }
        if (HibernateUtils.isConvertable(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }
    }
    @InheritInverseConfiguration
    public abstract Order toEntity(OrderDto orderDto);

    public abstract List<Order> toEntity(List<OrderDto> list);

    @AfterMapping
    public void toEntityAfterMapping(OrderDto dto, @MappingTarget Order entity) {
        if (dto.getCustomer() != null) {
            entity.setCustomer(customerService.getById(dto.getCustomer().getId()).get());
        }
        if (dto.getEmployee() != null) {
            entity.setEmployee(employeeService.getById(dto.getEmployee().getId()).get());
        }
    }

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Order updateEntityFromDto(OrderDto dto, @MappingTarget Order order);

}
