package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.Customer;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.EmployeeMapper;
import com.rms.rest.modelmapper.PaginationMapper;
import com.rms.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ResponseEntity<EmployeeDto> getById(int id)
    {
        Employee employee =employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));
        EmployeeDto dto = mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<EmployeeDto> addEmployee (EmployeeDto employeeDto)
    {
        Employee employee = mapper.toEntity(employeeDto);
        employeeService.save(employee);
        EmployeeDto dto = mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<EmployeeDto> updateEmployee(EmployeeDto employeeDto , int id)
    {
        Employee employee =employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));
        mapper.updateEntityFromDto(employeeDto,employee);
        employeeService.save(employee);
        EmployeeDto dto=mapper.toDto(employee);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> deleteById (Integer id)
    {
        employeeService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(Employee.class.getSimpleName(),id));
        try {
            employeeService.deleteById(id);
        }
        catch (Exception exception)
        {
            throw new ResourceRelatedException(Customer.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
