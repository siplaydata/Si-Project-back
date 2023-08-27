package com.example.cocktail.CocktailSearch.model;

import com.example.cocktail.Main.Upload.model.Recipe.Cocktail;
import com.example.cocktail.Main.Upload.model.Recipe.Images;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "recipe")
public class SearchRecipe {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Cocktail cocktail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    private Images images;
}
