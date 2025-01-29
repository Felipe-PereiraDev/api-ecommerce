package com.br.ecommerce.dto.user;

import com.br.ecommerce.entity.Address;

public record ResponseAddressDTO(
        String state,
        String city,
        String street
) {
    public ResponseAddressDTO (Address address){
        this(
                address.getState(),
                address.getCity(),
                address.getStreet()
        );
    }
}
