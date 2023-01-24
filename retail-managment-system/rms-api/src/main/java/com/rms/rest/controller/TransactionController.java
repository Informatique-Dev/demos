package com.rms.rest.controller;

import com.rms.rest.dto.TransactionDto;
import com.rms.rest.handler.TransactionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Tag(name = "Transaction", description = "Rest Api For Transaction")
public class TransactionController {
    private TransactionHandler transactionHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all transactions")
    public ResponseEntity<?> getAll() {
        return transactionHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get transaction by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return transactionHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add transactions")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDto transaction) {
        return transactionHandler.addTransaction(transaction);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update transaction")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDto transaction,
                                               @PathVariable Integer id) {
        return transactionHandler.updateTransaction(transaction, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete transaction By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return transactionHandler.deleteById(id);
    }
}
