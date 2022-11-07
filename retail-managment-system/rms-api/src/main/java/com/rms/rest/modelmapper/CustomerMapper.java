package com.rms.rest.modelmapper;

import com.rms.domain.sales.Customer;
import com.rms.rest.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toCustomerDto(Customer customer);

    List<CustomerDto> toCustomerDto(List<Customer> customers);

    Customer toCustomer(CustomerDto customerDto);

    List<Customer> toCustomer(List<CustomerDto> customerDtos);

    void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);
}
