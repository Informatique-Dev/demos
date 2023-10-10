package com.rms.rest.controller;

import com.rms.rest.dto.ProfitDetailsDto;
import com.rms.rest.handler.ProfitDetailsHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profitDetails")
@AllArgsConstructor
@Tag(name = "ProfitDetails", description = "Rest Api For ProfitDetails")
public class ProfitDetailsController {
    private ProfitDetailsHandler profitDetailsHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all profit Details")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return profitDetailsHandler.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get profit Details by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return profitDetailsHandler.getById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update profit Details")
    public ResponseEntity<?> updateProfit(@RequestBody ProfitDetailsDto profitDetailsdto, @PathVariable Integer id) {
        return profitDetailsHandler.update(profitDetailsdto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete profit By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return profitDetailsHandler.delete(id);
    }

}
