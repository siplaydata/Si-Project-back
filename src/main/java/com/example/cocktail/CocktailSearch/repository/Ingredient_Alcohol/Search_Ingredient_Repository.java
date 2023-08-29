package com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol;


import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Search_Ingredient_Repository extends JpaRepository<Search_Ingredient, Integer> {
    List<Search_Ingredient> findByKoreanIngredientContainingIgnoreCase(String ingredient);
    List<Search_Ingredient> findByEnglishIngredientContainingIgnoreCase(String ingredient);
}
