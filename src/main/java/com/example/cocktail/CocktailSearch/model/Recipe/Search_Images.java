package com.example.cocktail.CocktailSearch.model.Recipe;

import com.example.cocktail.Main.Upload.model.Recipe.Recipe;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Search_Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "picture")
    private String picture;

    @OneToOne(mappedBy = "images", fetch = FetchType.LAZY)
    private Recipe recipe;
}
