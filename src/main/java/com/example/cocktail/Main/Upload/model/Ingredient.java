package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "testingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inum")
    private Long inum;

    @Column(name = "kingre")
    private String kingre;

    @Column(name = "eingre")
    private String eingre;

    @Column(name = "ingredient_korean")
    private String ingredientKorean;

    @Column(name = "ingredient_english")
    private String ingredientEnglish;

    @OneToMany(mappedBy = "ingredient")
    private List<Pair> pairs;
}
