package com.rms.rest.controller;

import com.rms.rest.dto.CustomerDto;
import com.rms.rest.handler.CustomerHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerHandler customerHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return customerHandler.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return customerHandler.getById(id);
    }

    @GetMapping("/{id}/installments")
    public ResponseEntity<?> getCustomerInstallments(@PathVariable("id") Integer id) {
        return customerHandler.getCustomerInstallments(id);
    }

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customer) {
        return customerHandler.addCustomer(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDto customer, @PathVariable Integer id) {
        return customerHandler.updateCustomer(customer);
    }
}
