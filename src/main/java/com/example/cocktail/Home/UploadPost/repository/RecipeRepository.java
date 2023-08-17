package com.example.cocktail.Home.UploadPost.repository;

//import com.example.cocktail.Home.UploadPost.model.Recipe;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Set;
//
//@Repository
//public interface RecipeRepository extends JpaRepository<Recipe, String> {
//    List<RecipeProjection> findByIngredientsIn(List<String> ingredientsText);
//    List<RecipeProjection> findByIngredientNumIn(List<String> ingredientNum);
//    @Query("SELECT DISTINCT r.ingredientNum FROM Recipe r WHERE r.ingredients IN :ingredientsText")
//    List<String> findDistinctIngredientNumByIngredientsIn(@Param("ingredientsText") Set<String> ingredientsText);
//    interface RecipeProjection {
//        String getCocktailName();
//        String getIngredients();
//        String getCmethod();
//        String getGarnish();
//        String getHistory();
//    }
//}

import com.example.cocktail.Home.UploadPost.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    List<Recipe> findByIdIn(List<String> ids);
}