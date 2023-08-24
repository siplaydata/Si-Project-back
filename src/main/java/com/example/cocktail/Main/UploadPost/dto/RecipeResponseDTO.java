package com.example.cocktail.Main.UploadPost.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeResponseDTO {
    private String ingredientType;
    private String userInputData;

    private String name;
    private String ingredients;
    private String cocktailMethod;
    private String garnish;
    private byte[] image;
}
