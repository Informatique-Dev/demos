package com.rms.rest.modelmapper;


import com.rms.domain.core.Employee;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.modelmapper.common.JPAEntityMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface EmployeeMapper extends JPAEntityMapper<Employee ,EmployeeDto> {

}
