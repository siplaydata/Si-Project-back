package com.example.demo.Home.GetList.TextChoice.service;

import com.example.demo.Home.GetList.TextChoice.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<String> getDistinctIngredientList() {
        return ingredientsRepository.findDistinctIngredients();
    }
}
