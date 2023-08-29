package com.example.cocktail.CocktailSearch.service.GetAll;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;
import com.example.cocktail.CocktailSearch.dto.Search_Recipe_All_DTO;
import com.example.cocktail.CocktailSearch.model.Recipe.Search_Recipe;
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

@Service
public class Search_GetAll_Service_Impl implements Search_GetAll_Service{
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
//    private Search_RecipeDTO buildRecipeDTO(Search_Recipe recipe, List<Search_Ingredient> ingredients, List<Search_Alcohol> alcohols) {
//        return new Search_RecipeDTO(
//                recipe.getId(),
//                null,
//                alcohols.stream().map(Search_Alcohol::getKoreanAlcohol).collect(Collectors.toList()),
//                alcohols.stream().map(Search_Alcohol::getEnglishAlcohol).collect(Collectors.toList()),
//                ingredients.stream().map(Search_Ingredient::getKoreanIngredient).collect(Collectors.toList()),
//                ingredients.stream().map(Search_Ingredient::getEnglishIngredient).collect(Collectors.toList()),
//                recipe.getCocktail().getKoreanName(),
//                recipe.getCocktail().getEnglishName(),
//                recipe.getIngredients(),
//                recipe.getMethod(),
//                recipe.getGarnish(),
//                recipe.getImages().getPicture()
//        );
//    }
//    @Override
//    public List<Search_RecipeDTO> getAllRecipes() {
//        List<Search_Recipe> recipesAll = searchRecipeRepository.findAll();
//        List<Search_RecipeDTO> recipeDTOs = new ArrayList<>();
//
//        for (Search_Recipe recipe : recipesAll) {
//            List<Integer> getIngredientIds = findByIngredientIdsByRecipeIds(recipe.getId());
//            List<Search_Ingredient> ingredients = findByIngredientByIds(getIngredientIds);
//            List<Integer> getAlcoholIds = findByIngredientIdsByAlcoholIds(ingredients);
//            List<Search_Alcohol> alcohols = findByAlcoholByAlcoholIds(getAlcoholIds);
//
//            Search_RecipeDTO recipeDTO = buildRecipeDTO(recipe, ingredients, alcohols);
//            recipeDTOs.add(recipeDTO);
//        }
//        return recipeDTOs;
//    }
//
//    private List<Search_Alcohol> findByAlcoholByAlcoholIds(List<Integer> getAlcoholIds) {
//        return searchAlcoholRepository.findAllById(getAlcoholIds);
//    }
//    private List<Integer> findByIngredientIdsByAlcoholIds(List<Search_Ingredient> ingredients) {
//        List<Integer> ingredientIds = ingredients.stream()
//                .map(Search_Ingredient::getId)
//                .collect(Collectors.toList());
//        return searchIngredientAlcoholRepository.findAlcoholIdsByIngredientIds(ingredientIds);
//    }
//    private List<Search_Ingredient> findByIngredientByIds(List<Integer> getIngredientIds) {
//        return searchIngredientRepository.findAllById(getIngredientIds);
//    }
//    private List<Integer> findByIngredientIdsByRecipeIds(int recipeId) {
//        return searchRecipeIngredientRepository.findByRecipeId(recipeId);
//    }
    public List<Search_Recipe_All_DTO> getAllRecipes() {
        List<Search_Recipe> recipes = searchRecipeRepository.findAll();

        return buildRecipes(recipes);
    }
    private List<Search_Recipe_All_DTO> buildRecipes(List<Search_Recipe> recipes) {
        List<Search_Recipe_All_DTO> recipeDtos = new ArrayList<>();
        for (Search_Recipe recipe : recipes) {
            Search_Recipe_All_DTO searchRecipeAllDto = new Search_Recipe_All_DTO(
                    recipe.getId(),
                    recipe.getCocktail().getKoreanName(),
                    recipe.getCocktail().getEnglishName(),
                    recipe.getIngredients(),
                    recipe.getMethod(),
                    recipe.getGarnish(),
                    recipe.getImages().getPicture()
            );

            recipeDtos.add(searchRecipeAllDto);
        }

        return recipeDtos;
    }
}
