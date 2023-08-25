package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "testrecipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnum")
    private Long cnum;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "cocktail_method")
    private String cocktailMethod;

    @Column(name = "garnish")
    private String garnish;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<Pair> pairs = new ArrayList<>();
}
