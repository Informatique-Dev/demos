package com.rms.rest.handler;

import com.rms.domain.core.Product;
import com.rms.domain.sales.Customer;
import com.rms.domain.sales.Installment;
import com.rms.domain.sales.Order;
import com.rms.domain.sales.OrderItem;
import com.rms.rest.dto.OrderDto;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.rest.modelmapper.OrderMapper;
import com.rms.service.CustomerService;
import com.rms.service.InstallmentService;
import com.rms.service.OrderService;
import com.rms.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderHandler {
    private OrderService orderService;
    private CustomerService customerService;
    private ProductService productService;
    private InstallmentService installmentService;
    private OrderMapper mapper;
    private InstallmentMapper installmentMapper;

    public ResponseEntity<?> add(OrderDto orderDto) {
        //TODO: use getById instead of loadById
        Order order = mapper.toOrder(orderDto);
        Customer customer = customerService.getById(order.getCustomer().getId());
        for (OrderItem item : order.getOrderItems()) {
            Product product = productService.getById(item.getProduct().getId());
            item.setProduct(product);
        }
        order.setCustomer(customer);
        order.setOrderDate(new Date());
        List<Installment> installments = new ArrayList<>();

        if (order.getPaymentType().equals((short) 2)) {
            installments = addInstallments(order);
        }
        orderService.save(order);
        if (!installments.isEmpty())
            installmentService.save(installments);
        OrderDto dto = mapper.toOrderDto(order);
        dto.setInstallments(installmentMapper.toInstallmentDto(installments));
        return ResponseEntity.ok(dto);
    }

    private List<Installment> addInstallments(Order order) {
        List<Installment> installments = new ArrayList<>();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate dueDate = LocalDate.now();
        double total = order.getOrderItems().stream().mapToDouble(d -> d.getUnitPrice() * d.getQuantity()).sum();
        total -= order.getPaidAmount();
        int noOfInstallments = (int) Math.ceil(total / order.getInstallmentAmount());

        for (int i = 0; i < noOfInstallments; i++) {
            Installment installment = new Installment();
            dueDate = dueDate.plusMonths(1);
            double installmentAmount = total > order.getInstallmentAmount() ? order.getInstallmentAmount() : total;
            total -= order.getInstallmentAmount();

            installment.setDueDate(Date.from(dueDate.atStartOfDay(defaultZoneId).toInstant()));
            installment.setInstallmentAmount(installmentAmount);
            installment.setStatus((short) 1);

            installment.setOrder(order);
            installment.setPaymentDate(null);
            installment.setPaymentAmount(null);
            installments.add(installment);
        }
        return installments;
    }
}
