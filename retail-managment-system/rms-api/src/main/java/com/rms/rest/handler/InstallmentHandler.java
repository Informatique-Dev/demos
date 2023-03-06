package com.rms.rest.handler;

import com.rms.domain.sales.Customer;
import com.rms.domain.sales.Installment;
import com.rms.domain.sales.Order;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.CustomerService;
import com.rms.service.InstallmentService;
import com.rms.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class InstallmentHandler {
    private InstallmentService installmentService;
    private InstallmentMapper mapper;
    private OrderService orderService;
    private CustomerService customerService ;

    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Integer id) {
        Installment installment = installmentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Installment.class.getSimpleName(),id));
        InstallmentDto dto = mapper.toDto(installment);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> getDueInstallments() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate endDate = LocalDate.now();
        endDate = endDate.plusMonths(1);
        List<Installment> installments = installmentService.getDueInstallments(new Date(), Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()));
        List<InstallmentDto> dtos = mapper.toDto(installments);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getAll(Integer page ,Integer size)
    {
        Page<Installment> installments = installmentService.getAll(page,size);
        List<InstallmentDto> dtos = mapper.toDto(installments.getContent());
        PaginatedResultDto<InstallmentDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toDto(installments));
        return ResponseEntity.ok(paginatedResult);

    }

    public ResponseEntity<?> save(InstallmentDto installmentDto) {
        Installment installment = mapper.toEntity(installmentDto);
        InstallmentDto dto = mapper.toDto(installmentService.save(installment));
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(InstallmentDto installmentDto, Integer id) {
        Installment installment = installmentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Installment.class.getSimpleName(),id));
        mapper.updateEntityFromDto(installmentDto, installment);
        installmentService.save(installment);
        InstallmentDto dto = mapper.toDto(installment);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
       Installment installment =  installmentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Installment.class.getSimpleName(),id));
        try {
            installmentService.delete(installment);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Installment.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

    public ResponseEntity<?> getByCustomerNationalId(String nationalId , Integer page , Integer size)
    {
           Customer customer = customerService.findNationalId(nationalId)
                   .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(),nationalId));
        Page<Installment> installments = installmentService.getByCustomerNationalId(customer.getNationalId() , page, size);
        List<InstallmentDto> dtos = mapper.toDto(installments.getContent());
        PaginatedResultDto<InstallmentDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toDto(installments));
        return ResponseEntity.ok(paginatedResult);
    }





}
