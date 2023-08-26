package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "picture")
    private String picture;
}
