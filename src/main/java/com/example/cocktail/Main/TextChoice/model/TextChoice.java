package com.example.cocktail.Main.TextChoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient")
public class TextChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ingredient_korean")
    private String ingredientKorean;

    @Column(name = "ingredient_english")
    private String ingredientEnglish;

//    @Column(name = "alcohol_korean")
//    private String alcoholKorean;
//
//    @Column(name = "alcohol_english")
//    private String alcoholEnglish;
}
