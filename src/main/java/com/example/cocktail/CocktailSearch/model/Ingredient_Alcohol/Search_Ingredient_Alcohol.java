package com.example.cocktail.CocktailSearch.model.Ingredient_Alcohol;

import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Ingredient;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredient_alcohol")
public class Search_Ingredient_Alcohol {
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
