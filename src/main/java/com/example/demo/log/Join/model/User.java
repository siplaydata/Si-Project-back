package com.example.demo.log.Join.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userPassword;
    private String name;
    private String address;
    private String phoneNumber;
    private String residentRegistrationNumber; // 주민번호
}
