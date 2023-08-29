package com.example.cocktail.CocktailSearch.repository.Recipe;

import com.example.cocktail.CocktailSearch.model.Recipe.Search_Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Search_Cocktail_Repository extends JpaRepository<Search_Cocktail, Integer> {
    List<Search_Cocktail> findByKoreanNameContainingIgnoreCase(String cocktailName);

    List<Search_Cocktail> findByEnglishNameContainingIgnoreCase(String cocktailName);
}
