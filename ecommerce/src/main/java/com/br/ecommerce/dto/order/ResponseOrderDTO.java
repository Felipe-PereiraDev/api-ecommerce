package com.br.ecommerce.dto.order;

import com.br.ecommerce.entity.Order;
import com.br.ecommerce.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ResponseOrderDTO(
        String username,
        String orderId,
        LocalDateTime orderDate,
        List<ResponseOrderProduct> productsProcessed,
        Double totalAmount,
        String status
) {
    public ResponseOrderDTO(Order order, List<ResponseOrderProduct> responseProducts) {
        this(
                order.getUser().getUsername(),
                order.getId().toString(),
                order.getOrderDate(),
                responseProducts,
                order.getTotalAmount(),
                order.getStatus().toString()
        );
    }
}
