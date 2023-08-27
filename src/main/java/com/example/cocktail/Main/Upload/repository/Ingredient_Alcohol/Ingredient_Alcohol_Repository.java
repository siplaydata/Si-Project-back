package com.example.cocktail.Main.Upload.repository.Ingredient_Alcohol;

import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Ingredient_Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Ingredient_Alcohol_Repository extends JpaRepository<Ingredient_Alcohol, Integer> {
    List<Ingredient_Alcohol> findByAlcoholId(int id);
}
