package com.example.cocktail.CocktailSearch.service.Alcohol;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Alcohol;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient_Alcohol;
import com.example.cocktail.CocktailSearch.model.Recipe.Search_Recipe;
import com.example.cocktail.CocktailSearch.model.Recipe_Ingredient.Search_Recipe_Ingredient;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Alcohol_Repository;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Ingredient_Alcohol_Repository;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Ingredient_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Cocktail_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Images_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Recipe_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe_Ingredient.Search_Recipe_Ingredient_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Search_Alcohol_Service_Impl implements Search_Alcohol_Service {
    @Autowired
    private Search_Alcohol_Repository searchAlcoholRepository;
    @Autowired
    private Search_Ingredient_Alcohol_Repository searchIngredientAlcoholRepository;
    @Autowired
    private Search_Ingredient_Repository searchIngredientRepository;
    @Autowired
    private Search_Cocktail_Repository searchCocktailRepository;
    @Autowired
    private Search_Images_Repository searchImagesRepository;
    @Autowired
    private Search_Recipe_Repository searchRecipeRepository;
    @Autowired
    private Search_Recipe_Ingredient_Repository searchRecipeIngredientRepository;
    public List<Search_RecipeDTO> searchByAlcoholByKeyword(String alcohol){
        List<Search_Alcohol> findAlcohols = findAlcohols(alcohol);
        List<Search_Ingredient> ingredients = findIngredients(findAlcohols);
        List<Search_Recipe_Ingredient> findRecipeIngredients = findRecipeIngredients(ingredients);

        return buildRecipeDTOs(findAlcohols, ingredients, findRecipeIngredients, alcohol);
    }

    private List<Search_Alcohol> findAlcohols(String alcohol) {
        if (alcohol.chars().anyMatch(c -> c >= '가' && c <= '힣')) {
            return searchAlcoholRepository.findByKoreanAlcoholContainingIgnoreCase(alcohol);
        } else {
            return searchAlcoholRepository.findByEnglishAlcoholContainingIgnoreCase(alcohol);
        }
    }

    private List<Search_Ingredient> findIngredients(List<Search_Alcohol> alcohols) {
        return alcohols.stream()
                .flatMap(alcoholObj -> {
                    List<Search_Ingredient_Alcohol> ingredientAlcohols = searchIngredientAlcoholRepository.findByAlcoholId(alcoholObj.getId());
                    return ingredientAlcohols.stream()
                            .map(ingredientAlcohol -> searchIngredientRepository.findById(ingredientAlcohol.getIngredientId()).orElse(null))
                            .filter(Objects::nonNull);
                })
                .collect(Collectors.toList());
    }

    private List<Search_Recipe_Ingredient> findRecipeIngredients(List<Search_Ingredient> ingredients) {
        return ingredients.stream()
                .flatMap(ingredient -> searchRecipeIngredientRepository.findByIngredientId(ingredient.getId()).stream())
                .collect(Collectors.toList());
    }
//    private List<Search_RecipeDTO> buildRecipeDTOs(List<Search_Alcohol> alcohols, List<Search_Ingredient> ingredients,
//                                            List<Search_Recipe_Ingredient> recipeIngredients, String alcohol) {
//        List<Search_RecipeDTO> recipeDTOs = new ArrayList<>();
//
//        recipeIngredients.forEach(recipeIngredient -> {
//            Search_Recipe recipe = searchRecipeRepository.findById(recipeIngredient.getRecipeId()).orElse(null);
//            if (recipe != null) {
//                Search_RecipeDTO recipeDTO = new Search_RecipeDTO(
//                        recipe.getId(),
//                        alcohol,
//
//                        alcohols.stream().map(Search_Alcohol::getKoreanAlcohol).collect(Collectors.toList()),
//                        alcohols.stream().map(Search_Alcohol::getEnglishAlcohol).collect(Collectors.toList()),
//
//                        ingredients.stream().map(Search_Ingredient::getKoreanIngredient).collect(Collectors.toList()),
//                        ingredients.stream().map(Search_Ingredient::getEnglishIngredient).collect(Collectors.toList()),
//
//                        recipe.getCocktail().getKoreanName(),
//                        recipe.getCocktail().getEnglishName(),
//
//                        recipe.getIngredients(),
//                        recipe.getMethod(),
//                        recipe.getGarnish(),
//                        recipe.getImages().getPicture()
//                );
//                recipeDTOs.add(recipeDTO);
//            }
//        });
//        return recipeDTOs;
//    }
    private List<Search_RecipeDTO> buildRecipeDTOs(List<Search_Alcohol> alcohols, List<Search_Ingredient> ingredients,
                                                   List<Search_Recipe_Ingredient> recipeIngredients, String alcohol) {
        List<Search_RecipeDTO> recipeDTOs = new ArrayList<>();

        // 중복을 제거한 recipeId들을 수집
        Set<Integer> uniqueRecipeIds = new HashSet<>();
        recipeIngredients.forEach(recipeIngredient -> uniqueRecipeIds.add(recipeIngredient.getRecipeId()));

        uniqueRecipeIds.forEach(recipeId -> {
            Search_Recipe recipe = searchRecipeRepository.findById(recipeId).orElse(null);
            if (recipe != null) {
                Search_RecipeDTO recipeDTO = new Search_RecipeDTO(
                        recipe.getId(),
                        alcohol,

                        alcohols.stream().map(Search_Alcohol::getKoreanAlcohol).collect(Collectors.toList()),
                        alcohols.stream().map(Search_Alcohol::getEnglishAlcohol).collect(Collectors.toList()),

                        ingredients.stream().map(Search_Ingredient::getKoreanIngredient).collect(Collectors.toList()),
                        ingredients.stream().map(Search_Ingredient::getEnglishIngredient).collect(Collectors.toList()),

                        recipe.getCocktail().getKoreanName(),
                        recipe.getCocktail().getEnglishName(),

                        recipe.getIngredients(),
                        recipe.getMethod(),
                        recipe.getGarnish(),
                        recipe.getImages().getPicture()
                );
                recipeDTOs.add(recipeDTO);
            }
        });

        return recipeDTOs;
    }
}