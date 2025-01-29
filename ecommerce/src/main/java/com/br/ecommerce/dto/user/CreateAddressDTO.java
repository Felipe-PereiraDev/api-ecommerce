package com.br.ecommerce.dto.user;

public record CreateAddressDTO(
        String state,
        String city,
        String street
) {
}
