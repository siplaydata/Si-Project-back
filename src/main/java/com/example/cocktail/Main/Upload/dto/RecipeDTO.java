package com.example.cocktail.Main.Upload.dto;

import lombok.Data;

@Data
public class RecipeDTO {
    private int id;
    private String ingredientType;
    private String userInputData;

    private String cocktailKorean;
    private String cocktailEnglish;
    private String ingredients;
    private String cocktailMethod;
    private String garnish;

    private String image;

    public RecipeDTO(int id, String ingredientType, String userInputData,
                     String cocktailKorean, String cocktailEnglish, String ingredients,
                     String cocktailMethod, String garnish, String image) {
        this.id = id;
        this.ingredientType = ingredientType;
        this.userInputData = userInputData;
        this.cocktailKorean = cocktailKorean;
        this.cocktailEnglish = cocktailEnglish;
        this.ingredients = ingredients;
        this.cocktailMethod = cocktailMethod;
        this.garnish = garnish;
        this.image = image;
    }
}
