package com.br.ecommerce.controller;

import com.br.ecommerce.dto.user.CreateAddressDTO;
import com.br.ecommerce.dto.user.CreateUserDTO;
import com.br.ecommerce.dto.user.ResponseUserDTO;
import com.br.ecommerce.entity.User;
import com.br.ecommerce.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseUserDTO> getUser(@PathVariable("id") String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new ResponseUserDTO(user));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Validated CreateUserDTO data) {
        var createdUser = userService.createUser(data);
        String url = "/user/" + createdUser.getId().toString();
        return ResponseEntity.created(URI.create(url)).build();
    }

    @PostMapping("/{username}/address")
    public ResponseEntity<?> addAddress(@PathVariable("username") String username,
                                        @RequestBody CreateAddressDTO data) {
        userService.addAddress(username, data);
        return ResponseEntity.ok().build();
    }
}
