package com.example.cocktail.CocktailSearch.repository;

import com.example.cocktail.CocktailSearch.model.SearchImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchImagesRepository extends JpaRepository<SearchImages, Integer> {
}
