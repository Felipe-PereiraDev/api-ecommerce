package com.br.ecommerce.dto.product;

import com.br.ecommerce.entity.Category;

public record ResponseCategoryDto(
        Long categoryId,
        String name,
        String description
) {
    public ResponseCategoryDto(Category category) {
        this(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
