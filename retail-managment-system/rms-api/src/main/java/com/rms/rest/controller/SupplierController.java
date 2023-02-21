package com.rms.rest.controller;

import com.rms.domain.purchase.Supplier;
import com.rms.rest.dto.SupplierDto;
import com.rms.rest.handler.SupplierHandler;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/supplier")
@Tag(name = "Supplier",description = "Rest API for Supplier")
public class SupplierController {

    private SupplierHandler supplierHandler;


    @GetMapping
    @Operation(summary = "get all" ,description = "this API to get all suppliers in pages")
    public ResponseEntity<?> getall(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size",defaultValue = "10") Integer size)
    {
       return supplierHandler.getAll(page,size);
    }
    @GetMapping("/{id}")
    @Operation(summary = "get by id" ,description = "this API to get supplier by id")
    public ResponseEntity<?>getById(@PathVariable Integer id)
    {
        return supplierHandler.getById(id);
    }
    @PostMapping
    @Operation(summary = "add supplier" ,description = "this API to add new supplier")
    public ResponseEntity<?>save(@Validated(InsertValidation.class) @RequestBody SupplierDto supplierDto)
    {
        return supplierHandler.save(supplierDto);
    }
    @PutMapping("/{id}")
    @Operation(summary = "update supplier" ,description = "this API to update an existing supplier")
    public ResponseEntity<?>update(@Validated(UpdateValidation.class) @RequestBody SupplierDto supplierDto,
                                   @PathVariable Integer id)
    {
       return supplierHandler.update(id,supplierDto);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete supplier" ,description = "this API to delete supplier")
    public ResponseEntity<?>delete(@PathVariable Integer id)
    {
        return supplierHandler.delete(id);
    }
}
