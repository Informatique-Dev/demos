package com.rms.rest.controller;

import com.rms.rest.dto.ProfitDto;
import com.rms.rest.handler.ProfitHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profits")
@AllArgsConstructor
public class ProfitController {
    private ProfitHandler profitHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return profitHandler.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return profitHandler.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> addProfit(@RequestBody ProfitDto profit) {
        return profitHandler.addProfit(profit);
    }
    @PatchMapping
    public ResponseEntity<?> distributeProfit() {
        return profitHandler.distributeProfit();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfit(@RequestBody ProfitDto profit, @PathVariable Integer id) {
        return profitHandler.updateProfit(profit);
    }
}
