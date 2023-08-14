package com.example.demo.Home.GetList.TextChoice.contorller;

import com.example.demo.Home.GetList.TextChoice.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;
    @GetMapping
    public List<String> getList(){
        return ingredientsService.getDistinctIngredientList();
    }
}
