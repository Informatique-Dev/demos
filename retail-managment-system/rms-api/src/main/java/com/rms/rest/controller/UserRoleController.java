package com.rms.rest.controller;

import com.rms.rest.handler.UserRoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userRoles")
@AllArgsConstructor
@Tag(name = "User Role", description = "REST API for User Role ")
public class UserRoleController {

    private UserRoleHandler userRoleHandler;


    @DeleteMapping("/{id}")
    @Operation(summary = "delete  By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return userRoleHandler.delete(id);
    }

}
