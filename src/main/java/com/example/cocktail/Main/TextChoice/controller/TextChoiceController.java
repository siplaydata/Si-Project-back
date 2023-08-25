package com.example.cocktail.Main.GetList.TextChoice.controller;

import com.example.cocktail.Main.GetList.TextChoice.dto.TextChoiceDTO;
import com.example.cocktail.Main.GetList.TextChoice.service.TextChoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TextChoiceController {
    @Autowired
    private TextChoiceService textChoiceService;
    @GetMapping
    public List<TextChoiceDTO> getAllTextChoices() {
        return textChoiceService.getAllTextChoices();
    }
}

