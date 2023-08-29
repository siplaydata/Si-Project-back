package com.example.cocktail.CocktailSearch.service.Alcohol;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;

import java.util.List;

public interface Search_Alcohol_Service {
    List<Search_RecipeDTO> searchByAlcoholByKeyword(String keyword);
}
