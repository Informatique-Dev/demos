package com.rms.rest.controller;

import com.rms.rest.dto.UserDto;
import com.rms.rest.handler.UserHandler;
import com.rms.rest.validation.InsertValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "REST API for User")
@AllArgsConstructor
public class UserController {
    private UserHandler userHandler;

    @GetMapping
    @Operation(summary = "Get All Users Paged")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        return userHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get User By Id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return userHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "add New User")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody UserDto userDto) {
        return userHandler.save(userDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update user")
    public ResponseEntity<?> update (@PathVariable Integer id , @RequestBody UserDto dto)
    {
        return userHandler.update(id, dto);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "update status withen true or false ")
    public ResponseEntity<?> updateStatus(@PathVariable Integer id,
                                          @RequestParam(value = "status",defaultValue = "true") Boolean status)
    {
        return userHandler.updateStatus(id,status);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete user By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return userHandler.delete(id);
    }



}
