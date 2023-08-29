package com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol;

import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient_Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Search_Ingredient_Alcohol_Repository extends JpaRepository<Search_Ingredient_Alcohol, Integer> {
    List<Search_Ingredient_Alcohol> findByAlcoholId(int id);

    @Query("SELECT sia.alcoholId FROM Search_Ingredient_Alcohol sia WHERE sia.ingredientId IN :ingredientIds")
    List<Integer> findAlcoholIdsByIngredientIds(@Param("ingredientIds") List<Integer> ingredientIds);

//    List<Integer> findAlcoholIdsByIngredientIdIn(List<Integer> ingredientIds);
}
