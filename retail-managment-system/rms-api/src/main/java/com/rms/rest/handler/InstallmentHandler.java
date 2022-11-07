package com.rms.rest.handler;

import com.rms.domain.sales.Installment;
import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.service.InstallmentService;
import lombok.AllArgsConstructor;
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

    public ResponseEntity<?> getById(Integer id) {
        Installment installment = installmentService.getById(id);
        InstallmentDto dto = mapper.toInstallmentDto(installment);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<?>> getDueInstallments() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate endDate = LocalDate.now();
        endDate = endDate.plusMonths(1);
        List<Installment> installments = installmentService.getDueInstallments(new Date(), Date.from(endDate.atStartOfDay(defaultZoneId).toInstant()));
        List<InstallmentDto> dtos = mapper.toInstallmentDto(installments);
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> add(InstallmentDto installmentDto) {
        Installment installment = mapper.toInstallment(installmentDto);
        installmentService.save(installment);
        InstallmentDto dto = mapper.toInstallmentDto(installment);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(InstallmentDto installmentDto) {
        Installment installment = installmentService.getById(installmentDto.getId());
        mapper.updateInstallmentFromDto(installmentDto, installment);
        installmentService.save(installment);
        InstallmentDto dto = mapper.toInstallmentDto(installment);
        return ResponseEntity.ok(dto);
    }
}
