package com.rms.rest.dto;

import com.rms.rest.dto.common.GenericDto;
import lombok.Data;

@Data
public class UserRoleDto extends GenericDto {
    private UserDto user;
    private RoleDto role;
}
