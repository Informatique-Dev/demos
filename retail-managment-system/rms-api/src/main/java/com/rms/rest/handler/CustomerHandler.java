package com.rms.rest.handler;

import com.rms.domain.sales.Customer;
import com.rms.domain.sales.Installment;
import com.rms.rest.dto.CustomerDto;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.modelmapper.CustomerMapper;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.service.CustomerService;
import com.rms.service.InstallmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomerHandler {
    private CustomerService customerService;
    private InstallmentService installmentService;
    private CustomerMapper mapper;
    private InstallmentMapper installmentMapper;

    public ResponseEntity<List<CustomerDto>> getAll() {
        List<Customer> customers = customerService.getAll();
        List<CustomerDto> dtos = mapper.toCustomerDto(customers);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<CustomerDto> getById(Integer id) {
        Customer customer = customerService.getById(id);
        CustomerDto dto = mapper.toCustomerDto(customer);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> getCustomerInstallments(Integer id) {
        List<Installment> installments = installmentService.getCustomerInstallments(id);
        List<InstallmentDto> installmentDtos = installmentMapper.toInstallmentDto(installments);

        return ResponseEntity.ok(installmentDtos);
    }

    public ResponseEntity<CustomerDto> addCustomer(CustomerDto customerDto) {
        Customer customer = mapper.toCustomer(customerDto);
        customerService.save(customer);
        CustomerDto dto = mapper.toCustomerDto(customer);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<CustomerDto> updateCustomer(CustomerDto customerDto) {
        Customer customer = customerService.getById(customerDto.getId());
        mapper.updateCustomerFromDto(customerDto, customer);
        customerService.save(customer);
        CustomerDto dto = mapper.toCustomerDto(customer);
        return ResponseEntity.ok(dto);
    }
}
