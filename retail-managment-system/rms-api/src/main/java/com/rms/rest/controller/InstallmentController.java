package com.rms.rest.controller;

import com.rms.rest.dto.InstallmentDto;
import com.rms.rest.handler.InstallmentHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installments")
@AllArgsConstructor
public class InstallmentController {
    private InstallmentHandler installmentHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return installmentHandler.getDueInstallments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return installmentHandler.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody InstallmentDto installment) {
        return installmentHandler.add(installment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody InstallmentDto installment, @PathVariable Integer id) {
        return installmentHandler.update(installment);
    }
}
