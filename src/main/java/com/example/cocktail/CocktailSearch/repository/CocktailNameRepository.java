package com.example.cocktail.CocktailSearch.repository;

import com.example.cocktail.CocktailSearch.model.SearchName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CocktailNameRepository extends JpaRepository<SearchName, Integer> {
    List<SearchName> findByKoreanNameContainingOrEnglishNameContaining(String keyword, String keyword1);
}
