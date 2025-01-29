package com.br.ecommerce.dto.product;

import java.math.BigDecimal;

public record UpdateProductDto(
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        Long categoryId
) {

}
