package com.example.cocktail.Main.Upload.model.Recipe;

import com.example.cocktail.Main.Upload.model.Recipe_Ingredient.Recipe_Ingredient;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "method")
    private String method;

    @Column(name = "garnish")
    private String garnish;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<Recipe_Ingredient> recipeIngredients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cocktail cocktail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Images images;
}
