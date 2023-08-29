package com.example.cocktail.Main.Upload.model.Recipe;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cocktail")
public class Cocktail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_korean")
    private String koreanName;

    @Column(name = "name_english")
    private String englishName;

    @OneToOne(mappedBy = "cocktail", fetch = FetchType.LAZY)
    private Recipe recipe;
}
