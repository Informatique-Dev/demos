package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.Customer;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceAlreadyExistsException;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.modelmapper.EmployeeMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class EmployeeHandler {
    private EmployeeService employeeService;
    private EmployeeMapper mapper;
     private PaginationMapper paginationMapper ;



    public ResponseEntity<?> getAll(Integer page , Integer size)
    {
        Page<Employee> employees = employeeService.getAll(page, size);
        List<EmployeeDto> dtos =mapper.toDto(employees.getContent());
        PaginatedResultDto<EmployeeDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(employees));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> getById(int id)
    {
        Employee employee =employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));
        EmployeeDto dto = mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> save (EmployeeDto employeeDto)
    {
        Optional<String> existedNationalId = employeeService.findNationalId(employeeDto.getNationalId());
        if(existedNationalId.isPresent()){
            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
                    "NationalId", employeeDto.getNationalId(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        Employee employee = mapper.toEntity(employeeDto);
        employeeService.save(employee);
        EmployeeDto dto = mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(EmployeeDto employeeDto , int id)
    {
        Employee employee =employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));

        Optional<String> existedNationalId= employeeService.findNationalId(employeeDto.getNationalId());

        if(existedNationalId.isPresent() && !employee.getId().equals(id)){
            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
                    "NationalId", employeeDto.getNationalId(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        Employee entity =mapper.updateEntityFromDto(employeeDto,employee);
        employeeService.save(entity);
        EmployeeDto dto=mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete (Integer id)
    {
        Employee employee = employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));
        try {
            employeeService.delete(employee);
        }
        catch (Exception exception)
        {
            throw new ResourceRelatedException(Customer.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }

}
