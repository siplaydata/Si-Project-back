package com.example.cocktail.CBTI.service;


import com.example.cocktail.CBTI.dto.CocktailDTO;
import com.example.cocktail.CBTI.model.CBTI;
import com.example.cocktail.CBTI.model.CocktailName;
import com.example.cocktail.CBTI.model.CocktailImages;
import com.example.cocktail.CBTI.repository.CBTIRepository;
import com.example.cocktail.CBTI.repository.CBTINameRepository;
import com.example.cocktail.CBTI.repository.CBTIImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CBTIService {
    @Autowired
    private CBTINameRepository CBTINameRepository;
    @Autowired
    private CBTIImagesRepository CBTIImagesRepository;
    @Autowired
    private CBTIRepository cbtiRepository;
    private static int MIN_LEVEL = 0;
    private static int MAX_LEVEL = 5;

    private CocktailDTO mapCBTIToCocktailDTO(CBTI cbti, CocktailName cocktailName, CocktailImages image) {
        CocktailDTO cocktailDTO = new CocktailDTO();

        cocktailDTO.setId(cbti.getId());
        cocktailDTO.setLevel(cbti.getLevel());
        cocktailDTO.setTaste(cbti.getTaste());
        cocktailDTO.setPlace(cbti.getPlace());
        cocktailDTO.setKoreanName(cocktailName.getKoreanName());
        cocktailDTO.setEnglishName(cocktailName.getEnglishName());
        cocktailDTO.setImage(image.getPicture());

        return cocktailDTO;
    }

    public List<CocktailDTO> getCocktailsByLevelBetweenMinAndMax(String level, String taste, String place) {
        if ("높게".equals(level)) {
            MIN_LEVEL = 3;
        } else if ("낮게".equals(level)) {
            MAX_LEVEL = 4;
        }

        List<CBTI> cbtiList = cbtiRepository.findAllByLevelBetweenAndTasteAndPlace(MIN_LEVEL, MAX_LEVEL, taste, place);

        return cbtiList.stream()
                .map(cbti -> {
                    CocktailName cocktailName = CBTINameRepository.findById(cbti.getId()).orElse(new CocktailName());
                    CocktailImages image = CBTIImagesRepository.findById(cbti.getId()).orElse(new CocktailImages());
                    return mapCBTIToCocktailDTO(cbti, cocktailName, image);
                })
                .collect(Collectors.toList());
    }
}
