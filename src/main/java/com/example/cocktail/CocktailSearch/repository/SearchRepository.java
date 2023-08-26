package com.example.cocktail.CocktailSearch.repository;

import com.example.cocktail.CocktailSearch.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {
    List<Search> findByIngredientsContaining(String keyword);
}
