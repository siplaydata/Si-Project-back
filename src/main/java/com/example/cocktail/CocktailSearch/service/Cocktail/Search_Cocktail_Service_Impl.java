package com.example.cocktail.CocktailSearch.service.Cocktail;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Alcohol;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;
import com.example.cocktail.CocktailSearch.model.Recipe.Search_Cocktail;
import com.example.cocktail.CocktailSearch.model.Recipe.Search_Recipe;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Alcohol_Repository;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Ingredient_Alcohol_Repository;
import com.example.cocktail.CocktailSearch.repository.Ingredient_Alcohol.Search_Ingredient_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Cocktail_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Images_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe.Search_Recipe_Repository;
import com.example.cocktail.CocktailSearch.repository.Recipe_Ingredient.Search_Recipe_Ingredient_Repository;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Search_Cocktail_Service_Impl implements Search_Cocktail_Service{
    @Autowired
    private Search_Alcohol_Repository searchAlcoholRepository;
    @Autowired
    private Search_Ingredient_Alcohol_Repository searchIngredientAlcoholRepository;
    @Autowired
    private Search_Ingredient_Repository searchIngredientRepository;
    @Autowired
    private Search_Cocktail_Repository searchCocktailRepository;
    @Autowired
    private Search_Recipe_Repository searchRecipeRepository;
    @Autowired
    private Search_Recipe_Ingredient_Repository searchRecipeIngredientRepository;

    public List<Search_RecipeDTO> searchByCocktailByKeyword(String cocktailName){
        List<Search_Cocktail> cocktails = findByCocktail(cocktailName);
        List<Search_Recipe> recipes = findByRecipe(cocktails);
        List<Integer> ingredientIds = findByIngredientIds(recipes);
        List<Search_Ingredient> Ingredients = findByIngredient(ingredientIds);
        List<Integer> alcoholIds = findByAlcoholIdsByIngredientIds(Ingredients);
        List<Search_Alcohol> alcohols = findByAlcohol(alcoholIds);

        return DTO(recipes, Ingredients, alcohols, cocktailName);
    }

    private List<Search_Cocktail> findByCocktail(String cocktailName) {
        if (cocktailName.chars().anyMatch(c -> c >= '가' && c <= '힣')) {
            return searchCocktailRepository.findByKoreanNameContainingIgnoreCase(cocktailName);
        } else {
            return searchCocktailRepository.findByEnglishNameContainingIgnoreCase(cocktailName);
        }
    }
    private List<Search_Recipe> findByRecipe(List<Search_Cocktail> cocktailIds) {
        List<Integer> cocktailIdList = cocktailIds.stream()
                .map(Search_Cocktail::getId)
                .distinct()
                .collect(Collectors.toList());

        return searchRecipeRepository.findAllById(cocktailIdList);
    }
    private List<Integer> findByIngredientIds(List<Search_Recipe> recipes) {
        List<Integer> recipeIdList = recipes.stream()
                .map(Search_Recipe::getId)
                .collect(Collectors.toList());

        return searchRecipeIngredientRepository.findIngredientIdsByRecipeIds(recipeIdList);
    }
    private List<Search_Ingredient> findByIngredient(List<Integer> ingredientIds) {
        return searchIngredientRepository.findAllById(ingredientIds);
    }
    private List<Integer> findByAlcoholIdsByIngredientIds(List<Search_Ingredient> ingredients) {
        List<Integer> ingredientList = ingredients.stream()
                .map(Search_Ingredient::getId)
                .collect(Collectors.toList());
        return searchIngredientAlcoholRepository.findAlcoholIdsByIngredientIds(ingredientList);
    }
    private List<Search_Alcohol> findByAlcohol(List<Integer> alcoholIds){
        return searchAlcoholRepository.findAllById(alcoholIds);
    }
    private List<Search_RecipeDTO> DTO(List<Search_Recipe> recipes, List<Search_Ingredient> ingredients, List<Search_Alcohol> alcohols, String cocktailName) {
        List<Search_RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Search_Recipe recipe : recipes) {
            Search_RecipeDTO recipeDTO = new Search_RecipeDTO(
                    recipe.getId(),
                    cocktailName,
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
        return recipeDTOs;
    }
}
