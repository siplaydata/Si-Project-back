package com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol;

import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Ingredient_Alcohol;
import com.example.cocktail.Main.Upload.model.Recipe_Ingredient.Recipe_Ingredient;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ingredient")
public class Search_Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ingredient_korean")
    private String koreanIngredient;

    @Column(name = "ingredient_english")
    private String englishIngredient;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private List<Ingredient_Alcohol> ingredientAlcohols = new ArrayList<>();

    @OneToMany(mappedBy = "ingredient")
    private List<Recipe_Ingredient> recipeIngredients;
}
