package com.example.cocktail.CocktailSearch.service.Ingredient;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Search_Ingredient_Service_Impl implements Search_Ingredient_Service{
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
    @Override
    public List<Search_RecipeDTO> searchByIngredientByKeyword(String keyword) {
        List<Search_Ingredient> getIngredients = findIngredients(keyword);
        List<Integer> getAlcoholIds = findIngredientAlcohol(getIngredients);
        List<Search_Alcohol> alcohols = findAlcoholByIds(getAlcoholIds);
        List<Search_Recipe_Ingredient> getRecipes = findRecipesIdsByIngredientIds(getIngredients);

        return buildRecipesDTO(keyword, alcohols, getIngredients, getRecipes);
    }
    private List<Search_Ingredient> findIngredients(String ingredient){
        if (ingredient.chars().anyMatch(c -> c >= '가' && c <= '힣')) {
            return searchIngredientRepository.findByKoreanIngredientContainingIgnoreCase(ingredient);
        } else {
            return searchIngredientRepository.findByEnglishIngredientContainingIgnoreCase(ingredient);
        }
    }
    private List<Integer> findIngredientAlcohol(List<Search_Ingredient> ingredients){
        List<Integer> ingredientIds = ingredients.stream()
                .map(Search_Ingredient::getId)
                .collect(Collectors.toList());

        return searchIngredientAlcoholRepository.findAlcoholIdsByIngredientIds(ingredientIds);
    }

    private List<Search_Alcohol> findAlcoholByIds(List<Integer> alcoholIds) {
        return searchAlcoholRepository.findAllByIdIn(alcoholIds);
    }

    private List<Search_Recipe_Ingredient> findRecipesIdsByIngredientIds(List<Search_Ingredient> ingredients){
        List<Integer> ingredientIds = ingredients.stream()
                .map(Search_Ingredient::getId)
                .distinct()
                .collect(Collectors.toList());

        return searchRecipeIngredientRepository.findByIngredientIdIn(ingredientIds);
    }

    private List<Search_RecipeDTO> buildRecipesDTO(String keyword, List<Search_Alcohol> alcohols, List<Search_Ingredient> getIngredients, List<Search_Recipe_Ingredient> getRecipes) {
        List<Search_RecipeDTO> recipeDTOs = new ArrayList<>();

        for (Search_Recipe_Ingredient recipeIngredient : getRecipes) {
            Search_Recipe recipe = searchRecipeRepository.findById(recipeIngredient.getRecipeId()).orElse(null);

            if (recipe != null) {
                Search_RecipeDTO recipeDTO = new Search_RecipeDTO(
                        recipe.getId(),
                        keyword,
                        alcohols.stream().map(Search_Alcohol::getKoreanAlcohol).collect(Collectors.toList()),
                        alcohols.stream().map(Search_Alcohol::getEnglishAlcohol).collect(Collectors.toList()),
                        getIngredients.stream().map(Search_Ingredient::getKoreanIngredient).collect(Collectors.toList()),
                        getIngredients.stream().map(Search_Ingredient::getEnglishIngredient).collect(Collectors.toList()),
                        recipe.getCocktail().getKoreanName(),
                        recipe.getCocktail().getEnglishName(),
                        recipe.getIngredients(),
                        recipe.getMethod(),
                        recipe.getGarnish(),
                        recipe.getImages().getPicture()
                );
                recipeDTOs.add(recipeDTO);
            }
        }
        return recipeDTOs;
    }
}