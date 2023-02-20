package com.rms.rest.controller;

import com.rms.rest.dto.InvestorDto;
import com.rms.rest.handler.InvestorHandler;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investor")
@AllArgsConstructor
@Tag(name = "Investor", description = "Rest Api For Investor")
public class InvestorController {
    private InvestorHandler investorHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all investors")
    public ResponseEntity<?> getAll() {
        return investorHandler.getAll();
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get All", description = "this api for get all investors by pagination")
    public ResponseEntity<?> getAll(@RequestParam (value = "page" , defaultValue = "0") Integer page ,
                                    @RequestParam (value = "size" , defaultValue = "10") Integer size) {
        return investorHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get investor by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return investorHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new investor")
    public ResponseEntity<?> save(@Validated(InsertValidation.class)
                                      @RequestBody InvestorDto investor) {
        return investorHandler.save(investor);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update investor")
    public ResponseEntity<?> update(@Validated(UpdateValidation.class)
            @RequestBody InvestorDto investor, @PathVariable Integer id) {
        return investorHandler.update(investor, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete investor By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return investorHandler.delete(id);
    }
}
