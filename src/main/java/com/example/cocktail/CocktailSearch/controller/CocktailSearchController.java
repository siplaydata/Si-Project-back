package com.example.cocktail.CocktailSearch.controller;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;
import com.example.cocktail.CocktailSearch.service.CocktailSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class CocktailSearchController {
    @Autowired
    private CocktailSearchService cocktailSearchService;

    @GetMapping("/data")
    public List<SearchDTO> searchDTOS (){
        return null;
    }
}
