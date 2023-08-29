package com.example.cocktail.CocktailSearch.controller;

import com.example.cocktail.CocktailSearch.dto.Search_RecipeDTO;
import com.example.cocktail.CocktailSearch.dto.Search_Recipe_All_DTO;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Alcohol;
import com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol.Search_Ingredient;
import com.example.cocktail.CocktailSearch.service.Alcohol.Search_Alcohol_Service;
import com.example.cocktail.CocktailSearch.service.Cocktail.Search_Cocktail_Service;
import com.example.cocktail.CocktailSearch.service.GetAll.Search_GetAll_Service;
import com.example.cocktail.CocktailSearch.service.Ingredient.Search_Ingredient_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class CocktailSearchController {
    @Autowired
    private Search_GetAll_Service searchGetAllService;
    @Autowired
    private Search_Alcohol_Service searchAlcoholService;
    @Autowired
    private Search_Ingredient_Service searchIngredientService;
    @Autowired
    private Search_Cocktail_Service searchCocktailService;

    @GetMapping
    public List<Search_Recipe_All_DTO> getAllRecipes() {
        return searchGetAllService.getAllRecipes();
    }
    @PostMapping("/keyword")
    public List<Search_RecipeDTO> searchCocktails (@RequestParam(required = false) String keywordType,
                                                   @RequestParam(required = false) String keyword) {
        switch (keywordType){
            case "베이스술" : return searchAlcoholService.searchByAlcoholByKeyword(keyword);
            case "재료" : return searchIngredientService.searchByIngredientByKeyword(keyword);
            case "칵테일명" : return searchCocktailService.searchByCocktailByKeyword(keyword);
            default : return null;
        }
    }
}
