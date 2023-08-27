package com.example.cocktail.Main.Upload.repository.Recipe_Ingrecient;

import com.example.cocktail.Main.Upload.model.Recipe_Ingredient.Recipe_Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Recipe_Ingredient_Repository extends JpaRepository<Recipe_Ingredient, Integer> {
    List<Recipe_Ingredient> findByIngredientId(int id);
}
