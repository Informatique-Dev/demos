package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.security.User;
import com.rms.rest.dto.UserDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.UserMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.EmployeeService;
import com.rms.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserHandler {
    private UserService userService ;
    private UserMapper mapper ;
    private EmployeeService employeeService ;
    private PaginationMapper paginationMapper ;






    public ResponseEntity<?> getAll (Integer page , Integer size)
    {
        Page<User> users = userService.getAll(page,size);
        List<UserDto> userDtos = mapper.toDto(users.getContent());
        PaginatedResultDto<UserDto> paginatedResult =new PaginatedResultDto<>();
        paginatedResult.setData(userDtos);
        paginatedResult.setPagination(paginationMapper.toDto(users));
        return  ResponseEntity.ok(paginatedResult);

    }

    public ResponseEntity<?> getById (Integer id)
    {
        User user = userService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(User.class.getSimpleName() , id));
        return ResponseEntity.ok(mapper.toDto(user));
    }

    public ResponseEntity<?> save(UserDto dto)
    {
        Optional<User> existedUsername = userService.findUserName(dto.getUserName());
        existedUsername.ifPresent(e -> {
            throw new ResourceAlreadyExistsException(User.class.getSimpleName(), "User Name " ,
                    dto.getUserName(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        });
        User user =mapper.toEntity(dto);
        userService.save(user);
        UserDto userDto = mapper.toDto(user);
        return ResponseEntity.created(URI.create("/user/" + userDto.getId())).body(userDto);
    }
    public ResponseEntity<?> update (Integer id , UserDto userDto)
    {
        User existedUser= userService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(User.class.getSimpleName() , id));
        Optional<User> existedUsername = userService.findUserName(userDto.getUserName());

        if (existedUsername.isPresent() && !existedUsername.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException(User.class.getSimpleName(), "Username", userDto.getUserName(), ErrorCodes.DUPLICATE_RESOURCE.getCode());
        }
        if (userDto.getEmployee()!=null)
        {
            Employee employee = employeeService.getById(userDto.getEmployee().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), userDto.getEmployee().getId()));
        existedUser.setEmployee(employee);
        }
        mapper.updateEntityFromDto(userDto , existedUser);
        userService.save(existedUser);
        UserDto dto = mapper.toDto(existedUser);
        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<?> delete (Integer id)
    {
        User user= userService.getById(id).orElseThrow(
                ()-> new ResourceNotFoundException(User.class.getSimpleName() , id));
        try {
            userService.delete(user);
        } catch (Exception exception) {
            throw new ResourceRelatedException(User.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
