package com.example.cocktail.Main.Upload.repository;

import com.example.cocktail.Main.Upload.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByEnglishAlcoholContainingIgnoreCase(String ingredient);

    List<Ingredient> findByKoreanAlcoholContainingIgnoreCase(String ingredient);

    List<Ingredient> findByKoreanIngredientContainingIgnoreCase(String ingredient);

    List<Ingredient> findByEnglishIngredientContainingIgnoreCase(String ingredient);
}