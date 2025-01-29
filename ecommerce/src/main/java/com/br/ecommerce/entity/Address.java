package com.br.ecommerce.entity;

import com.br.ecommerce.dto.user.CreateAddressDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String city;
    private String street;

    public Address(Long id, String state, String city, String street) {
        this.id = id;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    public Address(CreateAddressDTO addressDTO) {
        this.state = addressDTO.state();
        this.city = addressDTO.city();
        this.street = addressDTO.street();
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
