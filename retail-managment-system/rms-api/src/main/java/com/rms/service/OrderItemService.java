package com.rms.service;

import com.rms.domain.sales.OrderItem;
import com.rms.repository.OrderItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderItemService {

    private OrderItemRepository orderItemRepository;

    public  OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public  OrderItem update(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> getById(Integer id) {
        return orderItemRepository.findById(id);
    }

    public Page<OrderItem>  getAll(Integer page , Integer size){
        return orderItemRepository.findAll(PageRequest.of(page,size));
    }
    public List<OrderItem>  getOrderItemsByOrderId(Integer orderId){
        return orderItemRepository.findOrderItemsByOrderId(orderId);
    }

    public void delete(OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
    }

}
