package com.example.cocktail.CBTI.repository;

import com.example.cocktail.CBTI.model.CocktailName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CBTINameRepository extends JpaRepository<CocktailName, Integer> {
}
