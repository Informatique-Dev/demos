package com.rms.rest.controller;

import com.rms.rest.dto.InvestorDto;
import com.rms.rest.handler.InvestorHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investors")
@AllArgsConstructor
public class InvestorController {
    private InvestorHandler investorHandler;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return investorHandler.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return investorHandler.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> addInvestor(@RequestBody InvestorDto investor) {
        return investorHandler.addInvestor(investor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvestor(@RequestBody InvestorDto investor, @PathVariable Integer id) {
        return investorHandler.updateInvestor(investor);
    }
}
