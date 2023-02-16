package com.rms.rest.controller;

import com.rms.rest.dto.RoleDto;
import com.rms.rest.handler.RoleHandler;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
@Tag(name = "Role", description = "Rest Api for Role")
public class RoleController {
    private RoleHandler roleHandler  ;

    @GetMapping
    @Operation(summary = "Get All" , description = "this api for get all roles")
    public ResponseEntity<?> getAll (@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                     @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return roleHandler.getAll(page,size);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get role by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return roleHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new role")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody RoleDto roleDto) {
        return roleHandler.save(roleDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update role")
    public ResponseEntity<?> update(
            @Validated (UpdateValidation.class)
            @RequestBody RoleDto roleDto,
            @PathVariable Integer id) {
        return roleHandler.update(id , roleDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete role By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return roleHandler.delete(id);
    }


}
