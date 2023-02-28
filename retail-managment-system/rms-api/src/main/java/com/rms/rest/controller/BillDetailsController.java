package com.rms.rest.controller;
import com.rms.rest.dto.BillDetailsDto;
import com.rms.rest.dto.OrderItemDto;
import com.rms.rest.handler.BillDetailsHandler;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bill-details")
@AllArgsConstructor
@Tag(name = "BillDetails", description = "Rest Api For BillDetails")
public class BillDetailsController {
    private BillDetailsHandler billDetailsHandler;
    @GetMapping
    @Operation(summary = "Get All" , description = "this api for get all BillDetails")
    public ResponseEntity<?> getAll( @RequestParam (value = "page",defaultValue = "0") Integer page,
                                     @RequestParam(value = "size",defaultValue = "10") Integer size){
        return billDetailsHandler.getAll(page,size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get BillDetails by id")
    public ResponseEntity<?> getById(@RequestParam("id") Integer id){
        return billDetailsHandler.getById(id);
    }
    @PostMapping
    @Operation(summary = "Add", description = "this api for add new BillDetails")
    public ResponseEntity<?> save(@Validated(InsertValidation.class) @RequestBody BillDetailsDto billDetailsDto){
        return billDetailsHandler.save(billDetailsDto);
    }
    @PutMapping("/{id}")
    @Operation(summary = "update", description = "this api for update new BillDetails")
    public ResponseEntity<?> update
            (@Validated(UpdateValidation.class)
           @RequestBody BillDetailsDto billDetailsDto,
             @PathVariable Integer id
            ){
        return billDetailsHandler.update(billDetailsDto,id);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "delete BillDetails By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return billDetailsHandler.delete(id);
    }
}
