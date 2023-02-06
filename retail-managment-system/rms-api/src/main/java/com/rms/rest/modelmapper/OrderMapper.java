package com.rms.rest.modelmapper;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.Order;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.OrderDto;
import com.rms.service.CustomerService;
import com.rms.service.EmployeeService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService  employeeService ;

    @Autowired
    private CustomerMapper customerMapper ;
    @Autowired
    private CustomerService customerService ;

//    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  //  @Mappings(source = "order.customer.id", target = "customer.id")
    @Mappings({
            @Mapping(source = "employee", target = "employee", ignore = true),
            @Mapping(source = "customer", target = "customer", ignore = true)})
    public abstract  OrderDto toOrderDto(Order order);

    public abstract  List<OrderDto> toOrderDto(List<Order> orders);

    @AfterMapping
    public void toDtoAfterMapping(Order entity, @MappingTarget OrderDto dto){
        if (HibernateUtils.isConvertable(entity.getCustomer())) {
            dto.setCustomer(customerMapper.toCustomerDto(entity.getCustomer()));
        }
        if (HibernateUtils.isConvertable(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }
    }



    @InheritInverseConfiguration
    public abstract Order toOrder(OrderDto orderDto);

    public abstract List<Order> toOrder(List<OrderDto> orderDtos);



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
    public abstract Order updateOrderFromDto(OrderDto orderItemDto, @MappingTarget Order orderItem);

}
