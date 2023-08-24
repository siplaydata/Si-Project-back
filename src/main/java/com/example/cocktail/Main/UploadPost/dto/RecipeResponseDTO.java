package com.example.cocktail.Main.UploadPost.dto;

import lombok.Data;

@Data
public class RecipeResponseDTO {
    private Long cnum;
    private String name;
    private String ingredients;
    private String cocktailMethod;
    private String garnish;
    private byte[] image;
}
