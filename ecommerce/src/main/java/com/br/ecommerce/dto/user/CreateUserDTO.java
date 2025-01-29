package com.br.ecommerce.dto.user;

public record CreateUserDTO(
        String username,
        String email,
        String password,
        String phone
) {
}
