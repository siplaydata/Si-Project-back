package com.example.cocktail.Main.Upload.repository.Recipe;

import com.example.cocktail.Main.Upload.model.Recipe.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Images_Repository extends JpaRepository<Images, Integer> {
}
