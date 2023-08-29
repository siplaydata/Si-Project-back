package com.example.cocktail.User.Info.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class InfoDTO {
    private String username;
    private String password;
    private String nickname;
    private String name;
    private String number;
    private String email;

    public InfoDTO(String username,
                   String password,
                   String nickname,
                   String name,
                   String number,
                   String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.number = number;
        this.email = email;
    }
}
