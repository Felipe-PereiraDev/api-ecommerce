package com.br.ecommerce.dto.order;

import com.br.ecommerce.entity.Product;

import java.math.BigDecimal;

public record ResponseOrderProduct(
        Long productId,
        String name,
        Long quantity,
        BigDecimal price
) {
    public ResponseOrderProduct(Product product, Long qtd) {
        this(
                product.getId(),
                product.getName(),
                qtd,
                product.getPrice()
        );
    }
}
