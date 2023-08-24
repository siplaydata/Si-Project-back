package com.example.cocktail.Main.GetList.TextChoice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient")
public class TextChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inum")
    private Long id;

    @Column(name = "kingre")
    private String kingre;

    @Column(name = "eingre")
    private String eingre;

    @Column(name = "ingredient_korean")
    private String ingredientKorean;

    @Column(name = "ingredient_english")
    private String ingredientEnglish;
}
