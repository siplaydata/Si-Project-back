package com.example.cocktail.CBTI.service;


import com.example.cocktail.CBTI.dto.CocktailDTO;
import com.example.cocktail.CBTI.model.CBTI;
import com.example.cocktail.CBTI.model.Cocktail;
import com.example.cocktail.CBTI.model.CocktailImages;
import com.example.cocktail.CBTI.repository.CBTIRepository;
import com.example.cocktail.CBTI.repository.CocktailRepository;
import com.example.cocktail.CBTI.repository.CocktailImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CBTIService {
    @Autowired
    private CocktailRepository cocktailRepository;
    @Autowired
    private CocktailImagesRepository cocktailImagesRepository;
    @Autowired
    private CBTIRepository cbtiRepository;
    private static int MIN_LEVEL = 0;
    private static int MAX_LEVEL = 5;

    private CocktailDTO mapCBTIToCocktailDTO(CBTI cbti) {
        int cbtiId = cbti.getId();
        int cbtiLevel = cbti.getLevel();
        String cbtiTaste = cbti.getTaste();
        String cbtiPlace = cbti.getPlace();

        Cocktail cocktail = cocktailRepository.findById(cbtiId)
                .orElse(new Cocktail()); // 예외가 발생하지 않을 때 대체값 설정

        CocktailImages image = cocktailImagesRepository.findById(cbtiId)
                .orElse(new CocktailImages()); // 예외가 발생하지 않을 때 대체값 설정

        CocktailDTO cocktailDTO = new CocktailDTO();
        cocktailDTO.setId(cbtiId);
        cocktailDTO.setLevel(cbtiLevel);
        cocktailDTO.setTaste(cbtiTaste);
        cocktailDTO.setPlace(cbtiPlace);
        cocktailDTO.setKoreanName(cocktail.getKoreanName());
        cocktailDTO.setEnglishName(cocktail.getEnglishName());
        cocktailDTO.setImage(image.getPicture());

        return cocktailDTO;
    }

    public List<CocktailDTO> getCocktailsByLevelBetweenMinAndMax(String level, String taste, String place) {
        if ("높게".equals(level)) { MIN_LEVEL = 4;
        } else if ("낮게".equals(level)) { MAX_LEVEL = 3; }

        List<CBTI> cbtiList = cbtiRepository.findAllByLevelBetweenAndTasteAndPlace(MIN_LEVEL, MAX_LEVEL, taste, place);
        return cbtiList.stream()
                .map(this::mapCBTIToCocktailDTO)
                .collect(Collectors.toList());
    }
}
