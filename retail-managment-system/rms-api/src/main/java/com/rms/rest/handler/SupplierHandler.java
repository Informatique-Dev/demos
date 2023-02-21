package com.rms.rest.handler;

import com.rms.domain.purchase.Supplier;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.SupplierDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.modelmapper.SupplierMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SupplierHandler {

    private SupplierService supplierService;
    private SupplierMapper supplierMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getAll(Integer page , Integer size)
    {
        Page<Supplier> suppliers=supplierService.getall(page,size);
        List<SupplierDto> dto=supplierMapper.toDto(suppliers.getContent());
        PaginatedResultDto<SupplierDto>paginatedResultDto=new PaginatedResultDto<>();
        paginatedResultDto.setData(dto);
        paginatedResultDto.setPagination(paginationMapper.toDto(suppliers));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?>getById(Integer id)
    {
        Supplier supplier=supplierService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Supplier.class.getSimpleName(),id));
        SupplierDto supplierDto =supplierMapper.toDto(supplier);
        return ResponseEntity.ok(supplierDto);

    }
    public ResponseEntity<?>save(SupplierDto supplierDto)
    {
        Supplier supplier=supplierMapper.toEntity(supplierDto);
        supplierService.save(supplier);
        SupplierDto dto=supplierMapper.toDto(supplier);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<?>update(Integer id,SupplierDto supplierDto)
    {
        Supplier supplier=supplierService.getById(id).orElseThrow(
                ()->new ResourceNotFoundException(Supplier.class.getSimpleName(),id));
        supplierMapper.updateEntityFromDto(supplierDto,supplier);
        supplier.setId(id);
        supplierService.save(supplier);
        SupplierDto dto =supplierMapper.toDto(supplier);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?>delete(Integer id) {
        Supplier supplier = supplierService.getById(id).orElseThrow(
                () -> new ResourceNotFoundException(Supplier.class.getSimpleName(), id));
        try {
            supplierService.delete(supplier);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Supplier.class.getSimpleName(), "id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }
}
