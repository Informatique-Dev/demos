package com.rms.rest.controller;

import com.rms.rest.dto.ProfitDto;
import com.rms.rest.handler.ProfitHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profits")
@AllArgsConstructor
@Tag(name = "Profit", description = "Rest Api For Profit")
public class ProfitController {
    private ProfitHandler profitHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all profits")
    public ResponseEntity<?> getAll() {
        return profitHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get profit by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return profitHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add profit")
    public ResponseEntity<?> addProfit(@RequestBody ProfitDto profit) {
        return profitHandler.addProfit(profit);
    }
    @PatchMapping
    @Operation(summary = "Distribute Profit", description = "this api for distribute profit")
    public ResponseEntity<?> distributeProfit() {
        return profitHandler.distributeProfit();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update profits")
    public ResponseEntity<?> updateProfit(@RequestBody ProfitDto profit, @PathVariable Integer id) {
        return profitHandler.updateProfit(profit, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete profit By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return profitHandler.deleteById(id);
    }
}
