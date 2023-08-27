package com.example.cocktail.Main.Upload.model.Ingredient_Alcohol;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "alcohol")
public class Alcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "alcohol_korean")
    private String koreanAlcohol;

    @Column(name = "alcohol_english")
    private String englishAlcohol;

    @OneToMany(mappedBy = "alcohol")
    private List<Ingredient_Alcohol> ingredientAlcohols;
}
