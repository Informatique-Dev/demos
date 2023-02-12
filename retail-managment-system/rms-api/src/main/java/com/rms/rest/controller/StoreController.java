package com.rms.rest.controller;

import com.rms.rest.dto.StoreDto;
import com.rms.rest.handler.StoreHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
@Tag(name = "Store", description = "Rest Api For Store")
public class StoreController {

    private StoreHandler storeHandler ;




    @GetMapping
    @Operation(summary = "Get All Stores by Employee Name", description = "this api for get all stores by employee name")
    public ResponseEntity getAll(@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                                    @RequestParam (value = "size" , defaultValue = "10") Integer size ,
                                                    @RequestParam(value ="responsible" , required = false)   String responsible)
    {
        return storeHandler.getAll(page, size, responsible);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get store by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return storeHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add store")
    public ResponseEntity<?> add(@RequestBody StoreDto storeDto) {
        return storeHandler.save(storeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update store")
    public ResponseEntity<?> update(@RequestBody StoreDto storeDto, @PathVariable Integer id) {
        return storeHandler.update(storeDto, id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete store By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return storeHandler.delete(id);
    }





}
