package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cocktail")
public class CocktailName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_korean")
    private String koreanName;

    @Column(name = "name_english")
    private String englishName;
}
