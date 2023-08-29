package com.example.cocktail.Main.Upload.repository.Recipe;

import com.example.cocktail.Main.Upload.model.Recipe.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cocktail_Repository extends JpaRepository<Cocktail, Integer> {
}
