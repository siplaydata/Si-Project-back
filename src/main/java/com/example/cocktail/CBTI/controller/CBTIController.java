package com.example.cocktail.CBTI.controller;

import com.example.cocktail.CBTI.dto.CocktailDTO;
import com.example.cocktail.CBTI.service.CBTIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cbti")
public class CBTIController {
    @Autowired
    private CBTIService cbtiService;

    @PostMapping
    public List<CocktailDTO> cocktailByOptions(
            @RequestParam(required = false) String level,
            @RequestParam(required = false) String taste,
            @RequestParam(required = false) String place ) {

        return cbtiService.getCocktailsByLevelBetweenMinAndMax(level, taste, place);
    }
}
