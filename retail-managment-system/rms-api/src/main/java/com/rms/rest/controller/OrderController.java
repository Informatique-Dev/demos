package com.rms.rest.controller;

import com.rms.rest.dto.OrderDto;
import com.rms.rest.handler.OrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Tag(name = "Order", description = "Rest Api For Order")
public class OrderController {
    private OrderHandler orderHandler;

    @GetMapping
    @Operation(summary = "Get All", description = "this api for get all orders")
    public ResponseEntity<?> getAll() {
        return orderHandler.getAll();
    }

    @PostMapping
    @Operation(summary = "Add", description = "this api for add new order")
    public ResponseEntity<?> add(@RequestBody OrderDto order) {
        return orderHandler.add(order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete order By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return orderHandler.deleteById(id);
    }

}
