package com.vishwa.smartbank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String password;
}