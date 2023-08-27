package com.example.cocktail.Main.Upload.service;

import com.example.cocktail.Main.Upload.dto.RecipeDTO;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import com.example.cocktail.Main.Upload.model.Recipe.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Upload_Service {
    List<RecipeDTO> getRecipes(MultipartFile pictureData);
}
