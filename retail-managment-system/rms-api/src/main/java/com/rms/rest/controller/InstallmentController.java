package com.rms.rest.controller;

import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.handler.InstallmentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installment")
@AllArgsConstructor
@Tag(name = "Installment", description = "Rest Api For Installment")
public class InstallmentController {
    private InstallmentHandler installmentHandler;

//    @GetMapping
//    @Operation(summary = "Get All", description = "this api for get all installments")
//    public ResponseEntity<?> getAll() {
//        return installmentHandler.getDueInstallments();
//    }


    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all installment")
    public ResponseEntity<?> getAll() {
        return installmentHandler.getAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get installment by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return installmentHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new installment")
    public ResponseEntity<?> add(@RequestBody InstallmentDto installment) {
        return installmentHandler.save(installment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update installment")
    public ResponseEntity<?> update(@RequestBody InstallmentDto installment, @PathVariable Integer id) {
        return installmentHandler.update(installment, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete installment By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return installmentHandler.delete(id);
    }
}
