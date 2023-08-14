package com.example.demo.Home.GetList.TextChoice.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "recipe")
public class Ingredients {
    @Id
    @Column(name = "재료")
    private String ingredients;
}
