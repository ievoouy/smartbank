package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.UserDTO;
import com.vishwa.smartbank.entity.User;

public interface UserService {
    User createUser(UserDTO userDTO);
}