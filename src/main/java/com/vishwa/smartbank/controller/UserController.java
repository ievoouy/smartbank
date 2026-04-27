package com.vishwa.smartbank.controller;

import com.vishwa.smartbank.dto.UserDTO;
import com.vishwa.smartbank.entity.User;
import com.vishwa.smartbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}