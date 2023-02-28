package com.rms.rest.controller;

import com.rms.rest.dto.BillDto;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.handler.BillHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
@AllArgsConstructor
@Tag(name = "Bill", description = "Rest Api For Bill")
public class BillController {
    private BillHandler billHandler;
    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all Bills")

    public ResponseEntity<?>getAll(@RequestParam(value = "page" , defaultValue = "0") Integer page,
                                   @RequestParam (value = "size" , defaultValue = "10") Integer id )
    {
       return billHandler.getAll(page,id);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get bill by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return billHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add bill")
    public ResponseEntity<?> save(@RequestBody BillDto bill) {
        return billHandler.save(bill);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update bill")
    public ResponseEntity<?> update(@RequestBody BillDto billDto, @PathVariable Integer id) {
        return billHandler.update(billDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete bill By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return billHandler.delete(id);
    }


}
