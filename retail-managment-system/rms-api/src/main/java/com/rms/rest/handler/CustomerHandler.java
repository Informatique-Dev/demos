package com.rms.rest.handler;

import com.rms.domain.sales.Customer;
import com.rms.domain.sales.Installment;
import com.rms.rest.dto.CustomerDto;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.CustomerMapper;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.service.CustomerService;
import com.rms.service.InstallmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CustomerHandler {
    private CustomerService customerService;
    private InstallmentService installmentService;
    private CustomerMapper mapper;
    private InstallmentMapper installmentMapper;

    public ResponseEntity<?> getAll() {
        List<Customer> customers = customerService.getAll();
        List<CustomerDto> dtos = mapper.toDto(customers);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getById(Integer id) {
        Customer customer = customerService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), id));
        CustomerDto dto = mapper.toDto(customer);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> getCustomerInstallments(Integer id) {
        List<Installment> installments = installmentService.getByCustomerInstallments(id);
        List<InstallmentDto> installmentDtos = installmentMapper.toDto(installments);

        return ResponseEntity.ok(installmentDtos);
    }


    public ResponseEntity<?> save(CustomerDto customerDto) {

        if (customerService.findNationalId(customerDto.getNationalId()).isPresent()) {
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName(), "National Id", customerDto.getNationalId(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        } else if (customerService.findCustomerCode(customerDto.getCustomerCode()).isPresent()) {
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName(), "Customer Code", customerDto.getCustomerCode(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        Customer customer = mapper.toEntity(customerDto);
        customerService.save(customer);
        CustomerDto dto = mapper.toDto(customer);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(CustomerDto customerDto, Integer id) {
        Customer customer = customerService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), id));
        Optional<Customer> customerNationalIdExist = customerService.findNationalId(customerDto.getNationalId());
        Optional<Customer> customerCodeExist = customerService.findCustomerCode(customerDto.getCustomerCode());
        if (customerNationalIdExist.isPresent() && !customerNationalIdExist.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName(), "National Id", customerDto.getNationalId(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        } else if (customerCodeExist.isPresent() && !customerCodeExist.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName(), "Customer Code", customerDto.getCustomerCode(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        Optional<Customer> exsitedTrustReceiptNo = customerService.getTrustReceiptNo(customerDto.getTrustReceiptNo());
        if (exsitedTrustReceiptNo.isPresent() && !exsitedTrustReceiptNo.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException(Customer.class.getSimpleName(),
                    "trustReceiptNo", Integer.toString(customerDto.getTrustReceiptNo()), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        mapper.updateEntityFromDto(customerDto, customer);
        customerService.save(customer);
        CustomerDto dto = mapper.toDto(customer);
        return ResponseEntity.ok(dto);

    }


    public ResponseEntity<?> delete(Integer id) {
        Customer customer = customerService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), id));
        try {
            customerService.delete(customer);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Customer.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
