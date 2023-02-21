package com.rms.rest.modelmapper;

import com.rms.domain.security.UserRole;
import com.rms.rest.dto.UserRoleDto;
import com.rms.service.RoleService;
import com.rms.service.UserService;
import com.rms.utils.HibernateUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserRoleMapper {

    @Autowired
    private UserMapper userMapper ;
    @Autowired

    private UserService userService ;
    @Autowired

    private RoleMapper roleMapper ;
    @Autowired

    private RoleService roleService;

    @Mappings({
            @Mapping(source = "user" , target = "user" ),
            @Mapping(source = "role" , target = "role" )
    })
    public abstract UserRoleDto toDto(UserRole userRole);

    public abstract List<UserRoleDto> toDto(List<UserRole> list);


    @AfterMapping
    public void toDtoAfterMapping(UserRole entity, @MappingTarget UserRoleDto dto) {
        if (HibernateUtils.isConvertable(entity.getUser())) {
            dto.setUser(userMapper.toDto(entity.getUser()));
        }
        if (HibernateUtils.isConvertable(entity.getRole())) {
            dto.setRole(roleMapper.toDto(entity.getRole()));
        }
    }

    @InheritInverseConfiguration
    public abstract UserRole toEntity(UserRoleDto userRoleDto);

    public abstract List<UserRole> toEntity(List<UserRoleDto> list);


    @AfterMapping
    public void toEntityAfterMapping(UserRoleDto dto, @MappingTarget UserRole entity) {
        if (dto.getUser() != null) {
            entity.setUser(userService.getById(dto.getId()).get());
        }
        if (dto.getRole() != null) {
            entity.setRole(roleService.getById(dto.getId()).get());
        }
    }
    @InheritInverseConfiguration
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract UserRole updateEntityFromDto(UserRoleDto dto, @MappingTarget UserRole entity);
}
