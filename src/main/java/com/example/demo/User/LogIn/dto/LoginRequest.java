package com.example.demo.User.LogIn.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}