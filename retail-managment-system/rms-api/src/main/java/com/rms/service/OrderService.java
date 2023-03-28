package com.rms.service;

import com.rms.domain.sales.Order;
import com.rms.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public Optional<Order> getById(Integer id) {
        return orderRepository.findById(id);
    }

    public Page<Order> getAll(Integer page , Integer size) {
        return orderRepository.findAll(PageRequest.of(page,size));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update (Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

}
