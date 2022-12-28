package com.rms.rest.controller;

import com.rms.rest.dto.TransactionDto;
import com.rms.rest.handler.TransactionHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {
    private TransactionHandler transactionHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return transactionHandler.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return transactionHandler.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDto transaction) {
        return transactionHandler.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDto transaction, @PathVariable Integer id) {
        return transactionHandler.updateTransaction(transaction);
    }
}
