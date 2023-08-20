package com.example.cocktail.Main.UploadPost.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "testingredient")
public class Ingredient {
    @Id
    @Column(name = "inum")
    private int inum;

    @Column(name = "ingredient")
    private String ingredient;

    @Column(name = "engingre")
    private String engingre;

    @Column(name = "type")
    private String type;
}
