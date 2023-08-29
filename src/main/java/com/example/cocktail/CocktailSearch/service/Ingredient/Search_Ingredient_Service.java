package com.example.cocktail.CocktailSearch.service.Ingredient;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;

import java.util.List;

public interface Search_Ingredient_Service {
    List<Search_RecipeDTO> searchByIngredientByKeyword(String keyword);
}
