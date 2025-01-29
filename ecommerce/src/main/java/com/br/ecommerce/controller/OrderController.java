package com.br.ecommerce.controller;

import com.br.ecommerce.dto.order.CreateOrderDTO;
import com.br.ecommerce.dto.order.ResponseOrderDTO;
import com.br.ecommerce.entity.Order;
import com.br.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseOrderDTO> createOrder(@PathVariable("userId") String userId,
                                                       @RequestBody @Validated CreateOrderDTO data) {
        ResponseOrderDTO responseOrderDTO = orderService.createOrder(userId, data);
        return ResponseEntity.ok(responseOrderDTO);
    }


}
