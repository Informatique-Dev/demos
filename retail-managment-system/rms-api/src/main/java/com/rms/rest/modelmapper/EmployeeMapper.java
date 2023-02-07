package com.rms.rest.modelmapper;


import com.rms.domain.core.Employee;
import com.rms.rest.dto.EmployeeDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface EmployeeMapper {

    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDto(List<Employee> employees);

    Employee toEntity(EmployeeDto employeeDto);

    List<Employee> toEntity(List<EmployeeDto> employeeDtos);

    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);


}
