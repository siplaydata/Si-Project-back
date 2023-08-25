package com.example.cocktail.CBTI.repository;

import com.example.cocktail.CBTI.model.CocktailImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailImagesRepository extends JpaRepository<CocktailImages, Integer> {
}
