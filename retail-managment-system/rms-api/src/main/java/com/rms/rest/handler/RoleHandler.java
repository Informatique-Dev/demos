package com.rms.rest.handler;

import com.rms.domain.security.Role;
import com.rms.rest.dto.RoleDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.modelmapper.RoleMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RoleHandler {
    private RoleService roleService ;
    private RoleMapper roleMapper ;
    private PaginationMapper paginationMapper ;

    public ResponseEntity<?> getAll(Integer page , Integer size)
    {
        Page<Role> roles = roleService.getAll(page,size);
        List<RoleDto> dtos = roleMapper.toDto(roles.getContent());
        PaginatedResultDto<RoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(dtos);
        paginatedResult.setPagination(paginationMapper.toDto(roles));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById (Integer id)
    {
        Role role = roleService.getById(id).orElseThrow(
                ()->  new ResourceNotFoundException(Role.class.getSimpleName() , id));
        RoleDto dto  = roleMapper.toDto(role);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> save (RoleDto roleDto)
    {
        Role role = roleMapper.toEntity(roleDto);
        roleService.save(role);
        RoleDto dto =roleMapper.toDto(role);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> update(Integer id, RoleDto roleDto) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        roleMapper.updateEntityFromDto(roleDto, role);
        roleService.save(role);
        RoleDto dto = roleMapper.toDto(role);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<?> delete(Integer id) {
        Role role = roleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Role.class.getSimpleName(), id));
        try {
            roleService.delete(role);
        }catch (Exception exception) {
            throw new ResourceRelatedException(Role.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }

}
