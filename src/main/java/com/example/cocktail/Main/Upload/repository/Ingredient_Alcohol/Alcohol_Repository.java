package com.example.cocktail.Main.Upload.repository.Ingredient_Alcohol;

import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Alcohol_Repository extends JpaRepository<Alcohol, Integer> {
    List<Alcohol> findByEnglishAlcoholContainingIgnoreCase(String ingredient);

    List<Alcohol> findByKoreanAlcoholContainingIgnoreCase(String ingredient);
}
