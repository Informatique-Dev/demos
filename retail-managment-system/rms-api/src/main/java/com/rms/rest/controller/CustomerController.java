package com.rms.rest.controller;

import com.rms.rest.dto.CustomerDto;
import com.rms.rest.handler.CustomerHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
@Tag(name = "Customers", description = "Rest Api For Customers")
public class CustomerController {
    private CustomerHandler customerHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all customers")
    public ResponseEntity<?> getAll() {
        return customerHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get customer by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return customerHandler.getById(id);
    }

    @GetMapping("/{id}/installments")
    @Operation(summary = "Get All Installments", description = "this api for get all installments of customer")
    public ResponseEntity<?> getCustomerInstallments(@PathVariable("id") Integer id) {
        return customerHandler.getCustomerInstallments(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new customer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customer) {
        return customerHandler.addCustomer(customer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update customer")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customer, @PathVariable Integer id) {
        return customerHandler.updateCustomer(customer, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete customer By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return customerHandler.deleteById(id);
    }
}
