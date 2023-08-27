package com.example.cocktail.CocktailSearch.service;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;
import com.example.cocktail.CocktailSearch.model.SearchRecipe;
import com.example.cocktail.CocktailSearch.model.SearchName;
import com.example.cocktail.CocktailSearch.repository.SearchImagesRepository;
import com.example.cocktail.CocktailSearch.repository.SearchNameRepository;
import com.example.cocktail.CocktailSearch.repository.SearchRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private SearchRecipeRepository searchRecipeRepository;
    @Autowired
    private SearchNameRepository searchNameRepository;
    @Autowired
    private SearchImagesRepository searchImagesRepository;

    private SearchDTO mapToSearchDTO(SearchRecipe searchRecipe) {
        return new SearchDTO(
                searchRecipe.getId(),
                searchRecipe.getIngredients(),
                searchRecipe.getMethod(),
                searchRecipe.getGarnish(),
                searchRecipe.getCocktail().getKoreanName(),
                searchRecipe.getCocktail().getEnglishName(),
                searchRecipe.getImages().getPicture()
        );
    }
    @Override
    public List<SearchDTO> searchAllCocktail() {
        return searchRecipeRepository.findAll().stream()
                .map(this::mapToSearchDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<SearchDTO> searchCocktailsByKeyword(String keyword) {
        List<SearchName> searchNames = searchNameRepository.findByKoreanNameContainingOrEnglishNameContaining(keyword, keyword);
        if (searchNames.isEmpty()) {
            return searchRecipeRepository.findByIngredientsContaining(keyword).stream()
                    .map(this::mapToSearchDTO)
                    .collect(Collectors.toList());
        }

        List<Integer> cocktailIds = searchNames.stream()
                .map(SearchName::getId)
                .collect(Collectors.toList());

        return cocktailIds.stream()
                .flatMap(id -> searchRecipeRepository.findById(id).stream())
                .map(this::mapToSearchDTO)
                .collect(Collectors.toList());
    }
}
