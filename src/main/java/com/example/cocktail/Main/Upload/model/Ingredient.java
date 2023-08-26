package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ingredient_korean")
    private String koreanIngredient;

    @Column(name = "ingredient_english")
    private String englishIngredient;

    @Column(name = "alcohol_korean")
    private String koreanAlcohol;

    @Column(name = "alcohol_english")
    private String englishAlcohol;

    @OneToMany(mappedBy = "ingredient")
    private List<RecipeIngredient> recipeIngredients;
}
