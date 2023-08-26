package com.example.cocktail.Main.Upload.dto;

import lombok.Data;

@Data
public class RecipeResponseDTO {
    private String ingredientType;
    private String userInputData;

    private String cocktailKorean;
    private String cocktailEnglish;
    private String ingredients;
    private String cocktailMethod;
    private String garnish;

    private String image;
}
