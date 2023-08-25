package com.example.cocktail.Main.UploadPost.repository;

import com.example.cocktail.Main.UploadPost.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByIngredientEnglishContainingIgnoreCase(String ingredient);
}