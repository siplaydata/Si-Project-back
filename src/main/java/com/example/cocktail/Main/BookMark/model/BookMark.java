package com.example.cocktail.Main.BookMark.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookmark")
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private BookMarkUser bookMarkUser;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private BookMarkRecipe bookMarkRecipe;
}


