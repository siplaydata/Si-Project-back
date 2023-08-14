package com.example.demo.Home.GetList.TextChoice.repository;

import com.example.demo.Home.GetList.TextChoice.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, String> {
    @Query("SELECT DISTINCT i.ingredients FROM Ingredients i WHERE i.ingredients NOT LIKE '%Unnamed%'")
    List<String> findDistinctIngredients();
}