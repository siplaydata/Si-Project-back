package com.example.cocktail.CocktailSearch.controller;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;
import com.example.cocktail.CocktailSearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class CocktailSearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping
    public List<SearchDTO> getAllCocktails() {
        return searchService.searchAllCocktail();
    }
    @PostMapping("/keyword")
    public List<SearchDTO> searchCocktails (@RequestParam(required = false) String keyword) {
        return searchService.searchCocktailsByKeyword(keyword);
    }
}
