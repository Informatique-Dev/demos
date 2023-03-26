package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.sales.*;
import com.rms.rest.dto.*;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.*;
import com.rms.rest.modelmapper.OrderMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderHandler {
    private OrderService orderService;
    private CustomerService customerService;
    private EmployeeService employeeService;
    private InstallmentHandler installmentHandler;
    private OrderMapper mapper;
    private PaginationMapper paginationMapper;
    private OrderItemHandler orderItemHandler;



    public ResponseEntity<?> getById(Integer id) {
        Order order = orderService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
        Customer customer = customerService.getById(order.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), order.getCustomer().getId()));
        Employee employee = employeeService.getById(order.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), order.getEmployee().getId()));
        OrderDto dto = mapper.toDto(order);
        return ResponseEntity.ok(dto);
    }


    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Order> orders = orderService.getAll(page, size);
        List<OrderDto> dtos = mapper.toDto(orders.getContent());
        PaginatedResultDto<OrderDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(orders));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<?> update(Integer id, OrderDto dto) {
        Order order = orderService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
        Employee employee = employeeService.getById(order.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), order.getEmployee().getId()));
        Customer customer = customerService.getById(order.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), order.getCustomer().getId()));
        order.setEmployee(employee);
        order.setCustomer(customer);
        mapper.updateEntityFromDto(dto, order);
        orderService.update(order);
        OrderDto orderDto = mapper.toDto(order);
        return ResponseEntity.ok(orderDto);
    }



    public ResponseEntity<OrderDto> save(OrderDto orderDto) {
        Customer customer = customerService.getById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(), orderDto.getCustomer().getId()));
        Employee employee = employeeService.getById(orderDto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), orderDto.getEmployee().getId()));
        Order order = mapper.toEntity(orderDto);
        order.setOrderDate(new Date());
        order.setCreatedBy(employee.getFullName());

        OrderDto dto = mapper.toDto(orderService.save(order));
        System.out.println("ordeeeeer"+order);


        addOrderItem(order.getId(), orderDto);
        List<OrderItemDto> orderItemsByOrderId = orderItemHandler.findOrderItemsByOrderId(dto.getId());
        double total = orderItemsByOrderId.stream().mapToDouble(d -> d.getProduct().getCashPrice() * d.getQuantity()).sum();
        dto.setTotalPrice(total);
        dto.setRemainingAmount(Math.abs(total-dto.getPaidAmount()));
        update(dto.getId(), dto);
        addInstallment(dto.getId() , orderDto);
        return ResponseEntity.ok(dto);
    }


    private void addOrderItem(Integer id, OrderDto dto) {
        Order order = orderService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
        OrderDto orderDto = mapper.toDto(order);
        List<OrderItemDto> orderItems = new ArrayList<>();
        for (OrderItemDto orderItemDto : dto.getOrderItems()) {
            orderItemDto.setOrder(orderDto);
            orderItemHandler.save(orderItemDto);
            orderItems.add(orderItemDto);
        }
//        List<OrderItemDto> orderItemsByOrderId = orderItemHandler.findOrderItemsByOrderId(id);
//        return orderItems;
    }

    private List<InstallmentDto> addInstallment(Integer id, OrderDto orderDto) {

        Order order = orderService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
        OrderDto dto = mapper.toDto(order);
        List<InstallmentDto> installmentDtos = new ArrayList<>();
        if (orderDto.getPaymentType().equals(PaymentType.INSTALLMENT) && orderDto.getInstallments() != null) {
            for (InstallmentDto installmentDto : orderDto.getInstallments()) {
                installmentDto.setOrder(dto);
                installmentDto.setInstallmentAmount(dto.getRemainingAmount());
                installmentDto.setRemainingAmount(installmentDto.getInstallmentAmount() - installmentDto.getPaymentAmount());
                if (installmentDto.getPaymentAmount() > dto.getRemainingAmount())
                {
                    installmentDto.setRemainingAmount(0.0);
                }
                installmentHandler.save(installmentDto);
                installmentDtos.add(installmentDto);
            }
        } else if (orderDto.getPaymentType().equals(PaymentType.CASH) && orderDto.getInstallments() != null ||
                orderDto.getPaymentType().equals(PaymentType.INSTALLMENT) && orderDto.getInstallments() == null) {
            throw new PaymentTypeNotValidException(PaymentType.class.getSimpleName(), orderDto.getPaymentType().name()
                    , ErrorCodes.PAYMENT_TYPE_NOT_VALID.getCode());
        }
        return installmentDtos;
    }

    public ResponseEntity<?> delete(Integer id) {
        Order order = orderService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
        try {
            orderService.delete(order);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Order.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
