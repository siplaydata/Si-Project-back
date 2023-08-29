package com.example.cocktail.Main.Upload.dto;

import lombok.Data;

@Data
public class RecipeDTO {
    private int id;
    private String userInputData;

    private String koreanAlcohol;
    private String englishAlcohol;

    private String koreanIngredient;
    private String englishIngredient;

    private String koreanCocktailName;
    private String englishCocktailName;

    private String ingredients;
    private String method;
    private String garnish;
    private String image;


    public RecipeDTO(int id,
                     String userInputData,
                     String koreanAlcohol,
                     String englishAlcohol,
                     String koreanIngredient,
                     String englishIngredient,
                     String koreanCocktailName,
                     String englishCocktailName,
                     String ingredients,
                     String method,
                     String garnish,
                     String image
    ) {
        this.id = id;
        this.userInputData = userInputData;
        this.koreanAlcohol = koreanAlcohol;
        this.englishAlcohol = englishAlcohol;
        this.koreanIngredient = koreanIngredient;
        this.englishIngredient = englishIngredient;
        this.koreanCocktailName = koreanCocktailName;
        this.englishCocktailName = englishCocktailName;
        this.ingredients = ingredients;
        this.method = method;
        this.garnish = garnish;
        this.image = image;
    }
}

