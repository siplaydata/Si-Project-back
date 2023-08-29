package com.example.cocktail.CocktailSearch.repository.Recipe_Ingredient;

import com.example.cocktail.CocktailSearch.model.Recipe.Search_Recipe;
import com.example.cocktail.CocktailSearch.model.Recipe_Ingredient.Search_Recipe_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface Search_Recipe_Ingredient_Repository extends JpaRepository<Search_Recipe_Ingredient, Integer> {
    List<Search_Recipe_Ingredient> findByIngredientId(int id);

    List<Search_Recipe_Ingredient> findByIngredientIdIn(List<Integer> ingredientIds);

    @Query("SELECT sri.ingredientId FROM Search_Recipe_Ingredient sri WHERE sri.recipeId IN :recipeIds")
    List<Integer> findByRecipeId(@Param("recipeIds") int recipeIds);

    @Query("SELECT sri.ingredientId FROM Search_Recipe_Ingredient sri WHERE sri.recipeId IN :recipeIds")
    List<Integer> findIngredientIdsByRecipeIds(@Param("recipeIds") List<Integer> recipeIds);

//    List<Integer> findIngredientIdsByRecipeIds(List<Integer> recipeIdList);
}
