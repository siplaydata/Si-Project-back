//package com.example.cocktail.Home.GetList.TextChoice.repository;
//
//import com.example.cocktail.Home.GetList.TextChoice.model.TextChoice;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface TextChoiceRepository extends JpaRepository<TextChoice, String> {
//    @Query("SELECT DISTINCT i.ingredients FROM Ingredients i WHERE i.ingredients NOT LIKE '%Unnamed%'")
//    List<String> findDistinctIngredients();
//}
