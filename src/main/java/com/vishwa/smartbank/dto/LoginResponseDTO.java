package com.vishwa.smartbank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private String message;
    private String email;

    private String token;
}