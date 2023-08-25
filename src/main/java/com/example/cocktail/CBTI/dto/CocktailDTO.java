package com.example.cocktail.CBTI.dto;

import lombok.Data;

@Data
public class CocktailDTO {
    private int id;

    private String englishName;
    private String koreanName;

    private int level;
    private String taste;
    private String place;

    private String image;
}
