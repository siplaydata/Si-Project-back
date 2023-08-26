package com.example.cocktail.CocktailSearch.service;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;

import java.util.List;

public interface SearchService {
    List<SearchDTO> searchAllCocktail();
    List<SearchDTO> searchCocktailsByKeyword(String keyword);
}
