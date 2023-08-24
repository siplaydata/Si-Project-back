package com.example.cocktail.Main.UploadPost.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pair")
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cnum;

    private Long inum;

    @ManyToOne
    @JoinColumn(name = "inum", referencedColumnName = "inum", insertable = false, updatable = false)
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "cnum", referencedColumnName = "cnum", insertable = false, updatable = false)
    private Recipe recipe;
}