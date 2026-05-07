package com.vishwa.smartbank.controller;

import com.vishwa.smartbank.dto.LoginDTO;
import com.vishwa.smartbank.dto.LoginResponseDTO;
import com.vishwa.smartbank.dto.UserDTO;
import com.vishwa.smartbank.entity.User;
import com.vishwa.smartbank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
}