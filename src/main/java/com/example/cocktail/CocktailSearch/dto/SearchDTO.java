package com.example.cocktail.CocktailSearch.dto;

import lombok.Data;

@Data
public class SearchDTO {
    private int id;
    private String koreanName;
    private String englishName;

    private String ingredients;
    private String method;
    private String garnish;

    private String image;

    public SearchDTO(int id, String ingredients, String method, String garnish, String koreanName, String englishName, String image) {
        this.id = id;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.ingredients = ingredients;
        this.method = method;
        this.garnish = garnish;
        this.image = image;
    }
}
