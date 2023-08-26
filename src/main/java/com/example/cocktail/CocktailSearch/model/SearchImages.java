package com.example.cocktail.CocktailSearch.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class SearchImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "picture")
    private String picture;
}
