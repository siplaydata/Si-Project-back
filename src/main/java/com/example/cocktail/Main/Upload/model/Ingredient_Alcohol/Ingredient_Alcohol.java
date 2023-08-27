package com.example.cocktail.Main.Upload.model.Ingredient_Alcohol;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient_alcohol")
public class Ingredient_Alcohol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int ingredientId;

    @Column(name = "alcohol_id")
    private int alcoholId;

    @ManyToOne
    @JoinColumn(name = "alcohol_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Alcohol alcohol;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Ingredient ingredient;
}