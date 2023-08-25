package com.example.cocktail.CocktailSearch.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe")
public class CocktailSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnum")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "cocktail_method")
    private String cocktailMethod;

    @Column(name = "garnish")
    private String garnish;

    @Column(name = "history")
    private String history;

    @Column(name = "clevel")
    private int level;

    @Column(name = "taste")
    private String taste;

    @Column(name = "smell")
    private String smell;

    @Column(name = "place")
    private String place;

    @Column(name = "color")
    private String color;

    @Column(name = "fruit")
    private String fruit;
}
