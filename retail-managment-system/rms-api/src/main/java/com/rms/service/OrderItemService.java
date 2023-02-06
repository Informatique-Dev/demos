package com.rms.service;

import com.rms.domain.sales.Order;
import com.rms.domain.sales.OrderItem;
import com.rms.repository.OrderItemRepository;
import com.rms.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService {

    private OrderItemRepository orderItemRepository;



    public Optional<OrderItem> getById(Integer id) {
        return orderItemRepository.findById(id);
    }

    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }



    public void deleteById(Integer id) {
        orderItemRepository.deleteById(id);
    }
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
