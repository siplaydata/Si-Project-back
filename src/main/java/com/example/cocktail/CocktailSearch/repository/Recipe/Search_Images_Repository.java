package com.example.cocktail.CocktailSearch.repository.Recipe;

import com.example.cocktail.CocktailSearch.model.Recipe.Search_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Search_Images_Repository extends JpaRepository<Search_Images, Integer> {
}
