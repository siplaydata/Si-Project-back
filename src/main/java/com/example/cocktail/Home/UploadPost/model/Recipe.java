package com.example.cocktail.Home.UploadPost.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cocktail")
public class Recipe {
    @Id
    @Column(name = "cnum")
    private String cocktailNum;

    @Column(name = "name")
    private String cocktailName;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "cmethod")
    private String cmethod;

    @Column(name = "garnish")
    private String garnish;

    @Column(name = "history")
    private String history;
}
