package com.rms.rest.handler;

import com.rms.domain.investor.Transaction;
import com.rms.domain.sales.Installment;
import com.rms.domain.sales.Order;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.service.InstallmentService;
import com.rms.service.OrderService;
import lombok.AllArgsConstructor;
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

    public ResponseEntity<?> getById(Integer id) {
        Installment installment = installmentService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Installment.class.getSimpleName(),id));
        InstallmentDto dto = mapper.toDto(installment);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<?>> getDueInstallments() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate endDate = LocalDate.now();
        endDate = endDate.plusMonths(1);
        List<Installment> installments = installmentService.getDueInstallments(new Date(), Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()));
        List<InstallmentDto> dtos = mapper.toDto(installments);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> getAll()
    {
        List<Installment> installments = installmentService.getAll();
        return ResponseEntity.ok(mapper.toDto(installments));

    }

    public ResponseEntity<?> save(InstallmentDto installmentDto) {
        Installment installment = mapper.toEntity(installmentDto);
        Order order = orderService.getById(installment.getOrder().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(),installment.getOrder().getId()));
        installment.setOrder(order);
        installmentService.save(installment);
        InstallmentDto dto = mapper.toDto(installment);
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
}
