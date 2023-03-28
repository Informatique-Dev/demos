package com.rms.rest.controller;

import com.rms.rest.dto.OrderDto;
import com.rms.rest.handler.OrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@Tag(name = "Order", description = "Rest Api For Order")
public class OrderController {
    private OrderHandler orderHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all orders")
    public ResponseEntity<?> getAll(@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                    @RequestParam (value = "size" , defaultValue = "10") Integer size) {
        return orderHandler.getAll(page,size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id", description = "this api for get order by id")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return orderHandler.getById(id);
    }


    @PostMapping
    @Operation(summary = "Add", description = "this api for add new order")
    public ResponseEntity<?> add(@RequestBody OrderDto order) {
        return orderHandler.save(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update order")
    public ResponseEntity<?> update( @PathVariable("id") Integer id,@RequestBody OrderDto orderDto) {
        return orderHandler.update(id, orderDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete order By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return orderHandler.delete(id);
    }

}
