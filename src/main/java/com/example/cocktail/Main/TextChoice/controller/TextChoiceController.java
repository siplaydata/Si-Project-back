package com.example.cocktail.Main.TextChoice.controller;

import com.example.cocktail.Main.TextChoice.dto.TextChoiceDTO;
import com.example.cocktail.Main.TextChoice.service.TextChoiceService;
import com.example.cocktail.Main.TextChoice.service.TextChoiceServiceImpl;
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

