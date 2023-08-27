package com.example.cocktail.Main.TextChoice.service;

import com.example.cocktail.Main.TextChoice.dto.TextChoiceDTO;
import com.example.cocktail.Main.TextChoice.model.TextChoice;
import com.example.cocktail.Main.TextChoice.repository.TextChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TextChoiceServiceImpl implements TextChoiceService {
    @Autowired
    private TextChoiceRepository textChoiceRepository;
    private TextChoiceDTO convertToDTO(TextChoice textChoice) {
        TextChoiceDTO dto = new TextChoiceDTO();
        dto.setIngredientKorean(textChoice.getIngredientKorean());
        dto.setIngredientEnglish(textChoice.getIngredientEnglish());

//        dto.setAlcoholKorean(textChoice.getAlcoholKorean());
//        dto.setAlcoholEnglish(textChoice.getAlcoholEnglish());
        return dto;
    }
    @Override
    public List<TextChoiceDTO> getAllTextChoices() {
        List<TextChoice> textChoices = textChoiceRepository.findAll();
        return textChoices.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}