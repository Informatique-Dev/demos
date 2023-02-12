package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.core.Store;
import com.rms.rest.dto.StoreDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.StoreMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.EmployeeService;
import com.rms.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StoreHandler {
     private StoreService storeService ;
     private EmployeeService employeeService;
    private StoreMapper mapper ;

    private PaginationMapper paginationMapper ;

    public ResponseEntity<?> getAll(Integer page , Integer size) {
        Page<Store> stores = storeService.getAll(page, size);
        List<StoreDto> dtos = mapper.toDto(stores.getContent());
        PaginatedResultDto<StoreDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(stores));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getStoreByResponsibleName(Integer page , Integer size , String responsible) {
        Page<Store> stores = storeService.getStoreByResponsibleName(page,size,responsible);
        List<StoreDto> storeDtoList = mapper.toDto(stores.getContent());
        PaginatedResultDto<StoreDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(storeDtoList);
        paginatedResultDto.setPagination(paginationMapper.toDto(stores));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<StoreDto> getById(Integer id) {
        Store store = storeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Store.class.getSimpleName(), id));
        StoreDto dto = mapper.toDto(store);
        return ResponseEntity.ok(dto);
    }



    public ResponseEntity<StoreDto> save(StoreDto storeDto) {
        Employee employee = employeeService.getById(storeDto.getResponsible().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), storeDto.getResponsible().getId()));
        Store store = mapper.toEntity(storeDto);
        store.setResponsible(employee);
        storeService.save(store);
        StoreDto dto = mapper.toDto(store);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<StoreDto> update(StoreDto storeDto, Integer id) {
        Store store = storeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Store.class.getSimpleName(), id));

        if(storeDto.getResponsible()!=null) {
            Employee employee = employeeService.getById(storeDto.getResponsible().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), storeDto.getResponsible().getId()));
            store.setResponsible(employee);
        }

        mapper.updateEntityFromDto(storeDto, store);
        storeService.save(store);
        StoreDto dto = mapper.toDto(store);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
       Store store = storeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Store.class.getSimpleName(),id));
        try {
            storeService.delete(store);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Store.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }
}
