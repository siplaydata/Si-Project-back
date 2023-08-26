package com.example.cocktail.CocktailSearch.service;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;
import com.example.cocktail.CocktailSearch.model.Search;
import com.example.cocktail.CocktailSearch.model.SearchImages;
import com.example.cocktail.CocktailSearch.model.SearchName;
import com.example.cocktail.CocktailSearch.repository.SearchImagesRepository;
import com.example.cocktail.CocktailSearch.repository.SearchNameRepository;
import com.example.cocktail.CocktailSearch.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private SearchNameRepository searchNameRepository;
    @Autowired
    private SearchImagesRepository searchImagesRepository;

    private SearchDTO mapToSearchDTO(Search search) {
        return new SearchDTO(
                search.getId(),
                search.getIngredients(),
                search.getMethod(),
                search.getGarnish(),
                search.getCocktailName().getKoreanName(),
                search.getCocktailName().getEnglishName(),
                search.getImages().getPicture()
        );
    }
    @Override
    public List<SearchDTO> searchAllCocktail() {
        return searchRepository.findAll().stream()
                .map(this::mapToSearchDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<SearchDTO> searchCocktailsByKeyword(String keyword) {
        List<SearchName> searchNames = searchNameRepository.findByKoreanNameContainingOrEnglishNameContaining(keyword, keyword);
        if (searchNames.isEmpty()) {
            return searchRepository.findByIngredientsContaining(keyword).stream()
                    .map(this::mapToSearchDTO)
                    .collect(Collectors.toList());
        }

        List<Integer> cocktailIds = searchNames.stream()
                .map(SearchName::getId)
                .collect(Collectors.toList());

        return cocktailIds.stream()
                .flatMap(id -> searchRepository.findById(id).stream())
                .map(this::mapToSearchDTO)
                .collect(Collectors.toList());
    }
}
