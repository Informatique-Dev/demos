package com.rms.rest.modelmapper;

import com.rms.domain.security.User;
import com.rms.rest.dto.UserDto;
import com.rms.service.EmployeeService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class UserMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService ;

    @Mapping(source = "employee", target = "employee")
    public abstract UserDto toDto(User user);

    public abstract List<UserDto> toDto(List<User> list);

    @AfterMapping
    public void toDtoAfterMapping(User entity, @MappingTarget UserDto dto){

        if (HibernateUtils.isConvertable(entity.getEmployee())) {
            dto.setEmployee(employeeMapper.toDto(entity.getEmployee()));
        }
    }

    @InheritInverseConfiguration
    public abstract User toEntity(UserDto userDto);

    public abstract List<User> toEntity(List<UserDto> list);

    @AfterMapping
    public void toEntityAfterMapping(UserDto dto, @MappingTarget User entity) {

        if (dto.getEmployee() != null) {
            entity.setEmployee(employeeService.getById(dto.getEmployee().getId()).get());
        }
    }
    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  User updateEntityFromDto(UserDto userDto, @MappingTarget User user);
}
