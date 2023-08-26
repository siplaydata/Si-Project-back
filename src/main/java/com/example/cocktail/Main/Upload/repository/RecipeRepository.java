package com.example.cocktail.Main.Upload.repository;

import com.example.cocktail.Main.Upload.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
