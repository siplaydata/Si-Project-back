package com.example.cocktail.CocktailSearch.repository;

import com.example.cocktail.CocktailSearch.model.SearchRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRecipeRepository extends JpaRepository<SearchRecipe, Integer> {
    List<SearchRecipe> findByIngredientsContaining(String keyword);
}
