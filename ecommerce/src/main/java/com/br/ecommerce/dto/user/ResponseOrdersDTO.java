package com.br.ecommerce.dto.user;

import com.br.ecommerce.entity.Order;

import java.time.LocalDateTime;

public record ResponseOrdersDTO(
        String orderId,
        LocalDateTime orderDate,
        Double totalAmount,
        String status
) {
    public ResponseOrdersDTO(Order order){
        this(
                order.getId().toString(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus().toString()
        );
    }
}
