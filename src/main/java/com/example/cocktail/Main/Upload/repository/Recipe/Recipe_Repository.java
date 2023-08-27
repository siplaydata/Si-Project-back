package com.example.cocktail.Main.Upload.repository.Recipe;

import com.example.cocktail.Main.Upload.model.Recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Recipe_Repository extends JpaRepository<Recipe, Integer> {
}
