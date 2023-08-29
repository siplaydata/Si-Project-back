package com.example.cocktail.CocktailSearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class Search_RecipeDTO {
    private int id;
    private String userInputData;

    private List<String> koreanAlcohol;
    private List<String> englishAlcohol;

    private List<String> koreanIngredient;
    private List<String> englishIngredient;

    private String koreanCocktailName;
    private String englishCocktailName;

    private String ingredients;
    private String method;
    private String garnish;
    private String image;

    public Search_RecipeDTO(int id,
                            String userInputData,

                            List<String> koreanAlcohol,
                            List<String> englishAlcohol,

                            List<String> koreanIngredient,
                            List<String> englishIngredient,

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
