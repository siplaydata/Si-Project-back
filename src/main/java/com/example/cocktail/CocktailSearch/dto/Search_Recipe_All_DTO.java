package com.example.cocktail.CocktailSearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class Search_Recipe_All_DTO {
    private int id;

    private String koreanCocktailName;
    private String englishCocktailName;

    private String ingredients;
    private String method;
    private String garnish;

    private String image;

    public Search_Recipe_All_DTO(
            int id,
            String koreanCocktailName,
            String englishCocktailName,
            String ingredients,
            String method,
            String garnish,
            String image
    ) {
        this.id = id;
        this.koreanCocktailName = koreanCocktailName;
        this.englishCocktailName = englishCocktailName;
        this.ingredients = ingredients;
        this.method = method;
        this.garnish = garnish;
        this.image = image;
    }
}
