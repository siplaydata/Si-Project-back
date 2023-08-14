package com.example.demo.User.LogIn.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class LoginUser {
    @Id
    private String username;
    private String password;
    private String nickname;
}