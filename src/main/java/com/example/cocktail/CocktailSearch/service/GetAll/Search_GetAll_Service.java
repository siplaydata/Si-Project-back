package com.example.cocktail.CocktailSearch.service.GetAll;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;
import com.example.cocktail.CocktailSearch.dto.Search_Recipe_All_DTO;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Alcohol;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;

import java.util.List;

public interface Search_GetAll_Service {
    List<Search_Recipe_All_DTO> getAllRecipes();
}
