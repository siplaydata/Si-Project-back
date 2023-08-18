package com.example.cocktail.User.Join.dto;

import lombok.Data;

@Data
public class JoinDTO {
    private String username;

    private String password;
    private String checkPassword;

    private String nickname;

    private String name;
    private String number;
    private String email;
}
