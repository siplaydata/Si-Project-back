package com.example.cocktail.CocktailSearch.repository;

import com.example.cocktail.CocktailSearch.model.CocktailSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailSearchRepository extends JpaRepository<CocktailSearch, Long> {
}
