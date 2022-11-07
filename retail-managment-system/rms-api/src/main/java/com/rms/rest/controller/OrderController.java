package com.rms.rest.controller;

import com.rms.rest.dto.OrderDto;
import com.rms.rest.handler.OrderHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private OrderHandler orderHandler;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody OrderDto order) {
        return orderHandler.add(order);
    }
}
