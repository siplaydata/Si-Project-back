package com.example.cocktail.CocktailSearch.repository.Recipe;

import com.example.cocktail.CocktailSearch.model.Recipe.Search_Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Search_Recipe_Repository extends JpaRepository<Search_Recipe, Integer> {
}
