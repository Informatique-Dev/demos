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
import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderHandler {
    private OrderService orderService;
    private CustomerService customerService;
    private EmployeeService employeeService;
    private InstallmentHandler installmentHandler ;
    private OrderMapper mapper;
    private PaginationMapper paginationMapper;
    private OrderItemHandler orderItemHandler ;


    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Order> orders = orderService.getAll(page, size);
        List<OrderDto> dtos = mapper.toDto(orders.getContent());
        PaginatedResultDto<OrderDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(dtos);
        paginatedResultDto.setPagination(paginationMapper.toDto(orders));
        return ResponseEntity.ok(paginatedResultDto);
    }
public ResponseEntity<?> update (Integer id , OrderDto dto)
{
    Order order = orderService.getById(id).
            orElseThrow(() -> new ResourceNotFoundException(Order.class.getSimpleName(), id));
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
        OrderDto dto = mapper.toDto(orderService.save(order));
        for (OrderItemDto orderItemDto :orderDto.getOrderItems())
        {
              orderItemDto.setOrder(dto);
              orderItemHandler.save(orderItemDto);
        }
        List<OrderItemDto> orderItems = orderItemHandler.findOrderItemsByOrderId(dto.getId());
        double total =0.0;
        for (OrderItemDto orderItemDto: orderItems) {
            total+= orderItemDto.getUnitPrice();
        }
        dto.setTotalPrice(total);
        update(dto.getId() , dto);
        if (orderDto.getPaymentType().equals(PaymentType.INSTALLMENT) && orderDto.getInstallments() !=null )
        {
            for (InstallmentDto installmentDto : orderDto.getInstallments()) {
                installmentDto.setOrder(dto);
                installmentHandler.save(installmentDto);
            }
        }
       else if (orderDto.getPaymentType().equals(PaymentType.CASH) && orderDto.getInstallments() != null ||
                orderDto.getPaymentType().equals(PaymentType.INSTALLMENT) && orderDto.getInstallments() ==null)
        {
           throw new PaymentTypeNotValidException(PaymentType.class.getSimpleName() ,orderDto.getPaymentType().name()
                   , ErrorCodes.PAYMENT_TYPE_NOT_VALID.getCode());
        }

        return ResponseEntity.ok(dto);
    }
//    private List<Installment> addInstallments(Order order) {
//        List<Installment> installments = new ArrayList<>();
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//        LocalDate dueDate = LocalDate.now();
//        double total = order.getOrderItems().stream().mapToDouble(d -> d.getUnitPrice() * d.getQuantity()).sum();
//        total -= order.getPaidAmount();
//        int noOfInstallments = (int) Math.ceil(total / order.getInstallmentAmount());
//
//        for (int i = 0; i < noOfInstallments; i++) {
//            Installment installment = new Installment();
//            dueDate = dueDate.plusMonths(1);
//            double installmentAmount = total > order.getInstallmentAmount() ? order.getInstallmentAmount() : total;
//            total -= order.getInstallmentAmount();
//
//            installment.setDueDate(Date.from(dueDate.atStartOfDay(defaultZoneId).toInstant()));
//            installment.setInstallmentAmount(installmentAmount);
//            installment.setStatus((short) 1);
//
//            installment.setOrder(order);
//            installment.setPaymentDate(null);
//            installment.setPaymentAmount(null);
//            installments.add(installment);
//        }
//        return installments;
//    }

    // TEST GENHINS
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
