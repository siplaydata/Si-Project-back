package com.example.cocktail.Main.GetList.TextChoice.service;

import com.example.cocktail.Main.GetList.TextChoice.dto.TextChoiceDTO;
import com.example.cocktail.Main.GetList.TextChoice.model.TextChoice;
import com.example.cocktail.Main.GetList.TextChoice.repository.TextChoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TextChoiceService {
    @Autowired
    private TextChoiceRepository textChoiceRepository;

    public List<TextChoiceDTO> getAllTextChoices() {
        List<TextChoice> textChoices = textChoiceRepository.findAll();
        return textChoices.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TextChoiceDTO convertToDTO(TextChoice textChoice) {
        TextChoiceDTO dto = new TextChoiceDTO();
        dto.setIngredientType(textChoice.getKingre());
        dto.setIngredientEnglish(textChoice.getIngredientEnglish());
        return dto;
    }
}