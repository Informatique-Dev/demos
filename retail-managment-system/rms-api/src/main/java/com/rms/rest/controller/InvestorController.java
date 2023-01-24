package com.rms.rest.controller;

import com.rms.rest.dto.InvestorDto;
import com.rms.rest.handler.InvestorHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investors")
@AllArgsConstructor
@Tag(name = "Investor", description = "Rest Api For Investor")
public class InvestorController {
    private InvestorHandler investorHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all investors")
    public ResponseEntity<?> getAll() {
        return investorHandler.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get investor by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return investorHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new investor")
    public ResponseEntity<?> addInvestor(@RequestBody InvestorDto investor) {
        return investorHandler.addInvestor(investor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update investor")
    public ResponseEntity<?> updateInvestor(@RequestBody InvestorDto investor, @PathVariable Integer id) {
        return investorHandler.updateInvestor(investor, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete investor By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return investorHandler.deleteById(id);
    }
}
