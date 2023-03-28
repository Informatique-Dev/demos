package com.rms.rest.controller;
import com.rms.rest.dto.OrderItemDto;
import com.rms.rest.handler.OrderItemHandler;
import com.rms.rest.validation.InsertValidation;
import com.rms.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-item")
@AllArgsConstructor
@Tag(name = "OrderItem", description = "Rest Api For OrderItem")
public class OrderItemController {
    private OrderItemHandler OrderItemHandler;


    @GetMapping
    @Operation(summary = "Get All" , description = "this api for get all OrderItem")
    public ResponseEntity<?> getAll (@RequestParam(value = "page" , defaultValue = "0") Integer page ,
                                     @RequestParam (value = "size" , defaultValue = "10") Integer size)
    {
        return OrderItemHandler.getAll(page,size);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update", description = "this api for update OrderItem")
    public ResponseEntity<?> update(
            @Validated (UpdateValidation.class)
            @RequestBody OrderItemDto orderItemDto,
            @PathVariable Integer id) {
        return OrderItemHandler.update(orderItemDto,id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete OrderItem By Id")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return OrderItemHandler.delete(id);
    }
}
