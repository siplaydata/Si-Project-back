package com.example.cocktail.CocktailSearch.service.Cocktail;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;

import java.util.List;

public interface Search_Cocktail_Service {
    List<Search_RecipeDTO> searchByCocktailByKeyword(String cocktailName);
}
