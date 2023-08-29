package com.example.cocktail.Main.BookMark.dto;

import lombok.Data;

@Data
public class BookMarkDTO {
    private int id;

    private String koreanCocktailName;
    private String englishCocktailName;

    private String ingredients;
    private String method;
    private String garnish;

    private String images;
}