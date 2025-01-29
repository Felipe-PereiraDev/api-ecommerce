package com.br.ecommerce.dto.user;

import com.br.ecommerce.entity.User;

import java.util.List;

public record ResponseUserDTO(
        String userId,
        String username,
        String email,
        ResponseAddressDTO address,
        String phone,
        List<ResponseOrdersDTO> orders
) {
    public ResponseUserDTO(User user) {
        this(
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getAddress() != null ? new ResponseAddressDTO(user.getAddress()) : null,
                user.getPhone(),
                user.getOrders() != null ? user.getOrders().stream().map(ResponseOrdersDTO::new).toList() : List.of()

        );
    }
}
