package com.br.ecommerce.dto.product;

import com.br.ecommerce.entity.Product;

import java.math.BigDecimal;

public record ResponseProductDto(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String category

) {
    public ResponseProductDto(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getName()
        );
    }
}
