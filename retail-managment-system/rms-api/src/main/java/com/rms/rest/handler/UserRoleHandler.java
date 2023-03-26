package com.rms.rest.handler;

import com.rms.domain.security.Role;
import com.rms.domain.security.User;
import com.rms.domain.security.UserRole;
import com.rms.rest.dto.RoleDto;
import com.rms.rest.dto.UserRoleDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceAlreadyExistsException;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.modelmapper.UserRoleMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.RoleService;
import com.rms.service.UserRoleService;
import com.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRoleHandler {

    private UserRoleService userRoleService ;
    private UserRoleMapper userRoleMapper ;
    private PaginationMapper paginationMapper ;
    private UserService userService ;
    private RoleService roleService ;



    public ResponseEntity<?> getById (Integer id)
    {
        UserRole userRole = userRoleService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(UserRole.class.getSimpleName() , id));
        return ResponseEntity.ok(userRoleMapper.toDto(userRole));
    }


    public ResponseEntity<?> save (Integer userId , RoleDto dto)
    {
        User user=userService.getById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(User.class.getSimpleName(), userId));

        Role role = roleService.getById(dto.getId()).orElseThrow(
                ()-> new ResourceNotFoundException(Role.class.getSimpleName() , dto.getId()));

        Optional<Integer> existingRole=userRoleService.getRoleIdByUserIdAndRoleId(userId,role.getId());
        if (existingRole.isPresent()){
            throw new ResourceAlreadyExistsException(UserRole.class.getSimpleName(),
                    "role", dto.getArabicName(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleService.save(userRole);
        UserRoleDto userRoleDto = userRoleMapper.toDto(userRole);
        return ResponseEntity.created(URI.create("/user/"+userId+"/role"   )).body(userRoleDto);
    }

    public ResponseEntity<?> delete(Integer id) {
        UserRole userRole = userRoleService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class.getSimpleName(), id));
        try {
            userRoleService.delete(userRole);
        } catch (Exception exception) {
            throw new ResourceRelatedException(UserRole.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> getUserRoles(Integer userId , Integer page ,Integer size){

        Page<UserRole> userRolesPage = userRoleService.getByUserId(userId, page, size);
        List<UserRoleDto> userRoleDtoList = userRoleMapper.toDto(userRolesPage.getContent());
        PaginatedResultDto<UserRoleDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(userRoleDtoList);
        paginatedResult.setPagination(paginationMapper.toDto(userRolesPage));
        return ResponseEntity.ok(paginatedResult);
    }
}
