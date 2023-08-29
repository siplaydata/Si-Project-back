package com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol;

import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Alcohol;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient_Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Search_Alcohol_Repository extends JpaRepository<Search_Alcohol, Integer> {
    List<Search_Alcohol> findByKoreanAlcoholContainingIgnoreCase(String alcohol);

    List<Search_Alcohol> findByEnglishAlcoholContainingIgnoreCase(String alcohol);

    List<Search_Alcohol> findAllByIdIn(List<Integer> uniqueAlcoholIds);
}
