package com.scaler.splitwise_mar22.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDTO {
    private String name;
    private String phoneNumber;
    private String password;
}

// DTO _> Data Transfer Objects
// We take a request dto as an input
// we send a response dto as output
