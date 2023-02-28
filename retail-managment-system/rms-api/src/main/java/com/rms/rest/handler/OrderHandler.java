package com.rms.rest.handler;

import com.rms.domain.core.Employee;
import com.rms.domain.core.Product;
import com.rms.domain.investor.Transaction;
import com.rms.domain.sales.Customer;
import com.rms.domain.sales.Installment;
import com.rms.domain.sales.Order;
import com.rms.domain.sales.OrderItem;
import com.rms.rest.dto.EmployeeDto;
import com.rms.rest.dto.OrderDto;
import com.rms.rest.dto.OrderItemDto;
import com.rms.rest.dto.ProductDto;
import com.rms.rest.dto.common.PaginatedResultDto;
import com.rms.rest.exception.ErrorCodes;
import com.rms.rest.exception.ResourceNotFoundException;
import com.rms.rest.exception.ResourceRelatedException;
import com.rms.rest.exception.Response;
import com.rms.rest.modelmapper.InstallmentMapper;
import com.rms.rest.modelmapper.OrderMapper;
import com.rms.rest.modelmapper.common.PaginationMapper;
import com.rms.service.*;
import lombok.AllArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    private  EmployeeService employeeService;
    private ProductService productService;
    private InstallmentService installmentService;
    private OrderMapper mapper;
    private InstallmentMapper installmentMapper;
    private OrderItemService orderItemService;
    private PaginationMapper paginationMapper ;


    public ResponseEntity<?> getAll(Integer page , Integer size) {
        Page<Order> orders = orderService.getAll(page, size);
        List<OrderDto> dtos =mapper.toDto(orders.getContent());
        PaginatedResultDto<OrderDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(orders));
        return ResponseEntity.ok(paginatedResultDto);
    }

    public ResponseEntity<OrderDto> save(OrderDto orderDto) {
        Customer customer = customerService.getById(orderDto.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Customer.class.getSimpleName(),orderDto.getCustomer().getId()));
        Employee employee = employeeService.getById(orderDto.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(),orderDto.getEmployee().getId()));

        Order order = mapper.toEntity(orderDto);

        //  List<OrderItem> items = new ArrayList<>();
//        for (OrderItem item : order.getOrderItems()) {
//            Product product = productService.getById(item.getProduct().getId())
//                    .orElseThrow(() -> new ResourceNotFoundException(Product.class.getSimpleName(),item.getProduct().getId()));
//            item.setProduct(product);
//           // items.add(item);
//        }
////        System.out.println("items array = "+items);
////        order.setOrderItems(items);
        order.setCustomer(customer);
        order.setEmployee(employee);
        //  order.setOrderItems(items);
        order.setOrderDate(new Date());
//        List<Installment> installments = new ArrayList<>();
//        System.out.println("installments");
//        if (order.getPaymentType().equals((short) 2)) {
//            installments = addInstallments(order);
//        }
        System.out.println("before save");
        orderService.save(order);
        System.out.println("after save");
//        if (!installments.isEmpty())
//            installmentService.save(installments);
        OrderDto dto = mapper.toDto(order);
//        dto.setInstallments(installmentMapper.toInstallmentDto(installments));
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

    // TEST GENHINS
    public ResponseEntity<?> delete(Integer id) {
       Order order= orderService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(),id));
        try {
            orderService.delete(order);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Order.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
