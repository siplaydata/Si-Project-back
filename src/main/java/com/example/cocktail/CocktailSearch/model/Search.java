package com.example.cocktail.CocktailSearch.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "method")
    private String method;

    @Column(name = "garnish")
    private String garnish;
}
