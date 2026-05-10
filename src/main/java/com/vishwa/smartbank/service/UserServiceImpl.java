package com.vishwa.smartbank.service;

import com.vishwa.smartbank.dto.LoginDTO;
import com.vishwa.smartbank.dto.LoginResponseDTO;
import com.vishwa.smartbank.dto.UserDTO;
import com.vishwa.smartbank.entity.User;
import com.vishwa.smartbank.exception.InvalidCredentialsException;
import com.vishwa.smartbank.exception.ResourceNotFoundException;
import com.vishwa.smartbank.repository.UserRepository;
import com.vishwa.smartbank.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean isPasswordMatch =
                passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

        if (!isPasswordMatch) {
            throw new InvalidCredentialsException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(
                "Login successful",
                user.getEmail(),
                token
        );
    }
}