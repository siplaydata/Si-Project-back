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
}
