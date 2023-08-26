package com.example.cocktail.Main.Upload.repository;

import com.example.cocktail.Main.Upload.model.CocktailName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailName, Integer> {
    CocktailName findById(int id);
}
