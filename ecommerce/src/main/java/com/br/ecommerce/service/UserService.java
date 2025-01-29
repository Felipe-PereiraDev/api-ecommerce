package com.br.ecommerce.service;

import com.br.ecommerce.dto.user.CreateAddressDTO;
import com.br.ecommerce.dto.user.CreateUserDTO;
import com.br.ecommerce.entity.Address;
import com.br.ecommerce.entity.User;
import com.br.ecommerce.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User findById(String uuid) {
        return userRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public User findByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(
               () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username não encontrado.")
       );
    }

    public User createUser(CreateUserDTO userDTO){
        User user = new User(userDTO);
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e){
            // mais tarde criar um response personalizado
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username ou E-mail ja existem");
        }
    }

    public void addAddress(String username, CreateAddressDTO addressDTO){
        Address address = new Address(addressDTO);

        User user = findByUsername(username);

        user.setAddress(address);

        userRepository.save(user);
    }



}
