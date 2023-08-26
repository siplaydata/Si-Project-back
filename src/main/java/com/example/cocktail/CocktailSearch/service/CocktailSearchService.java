package com.example.cocktail.CocktailSearch.service;

import com.example.cocktail.CocktailSearch.dto.SearchDTO;
import com.example.cocktail.CocktailSearch.model.SearchName;
import com.example.cocktail.CocktailSearch.model.Search;
import com.example.cocktail.CocktailSearch.repository.CocktailNameRepository;
import com.example.cocktail.CocktailSearch.repository.CocktailSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CocktailSearchService {
    @Autowired
    private CocktailSearchRepository cocktailSearchRepository;
    @Autowired
    private CocktailNameRepository cocktailNameRepository;
    private List<SearchDTO> mapToSearchDTOList(List<Search> searchList) {
        List<SearchDTO> searchDTOList = new ArrayList<>();
        for (Search search : searchList) {
            SearchDTO searchDTO = mapToSearchDTO(search);
            searchDTOList.add(searchDTO);
        }
        return searchDTOList;
    }
    private SearchDTO mapToSearchDTO(Search search) {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setId(search.getId());
        searchDTO.setIngredients(search.getIngredients());
        searchDTO.setMethod(search.getMethod());
        searchDTO.setGarnish(search.getGarnish());

        SearchName searchName = cocktailNameRepository.findById(search.getId())
                .orElse(new SearchName());

        searchDTO.setKoreanName(searchName.getKoreanName());
        searchDTO.setEnglishName(searchName.getEnglishName());

        return searchDTO;
    }
    public List<SearchDTO> searchAllCocktail() {
        List<Search> searchList = cocktailSearchRepository.findAll();
        return mapToSearchDTOList(searchList);
    }
    public List<SearchDTO> searchCocktailsByKeyword(String keyword) {
        List<SearchName> searchNames = cocktailNameRepository.findByKoreanNameContainingOrEnglishNameContaining(keyword, keyword);
        List<Integer> cocktailIds = searchNames.stream().map(SearchName::getId).collect(Collectors.toList());

        List<Search> searches = cocktailSearchRepository.findAllByIdIn(cocktailIds);
        return mapToSearchDTOList(searches);
    }
}
