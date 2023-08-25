package com.example.cocktail.CBTI.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cbti")
public class CBTI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "level")
    private int level;

    @Column(name = "taste")
    private String taste;

    @Column(name = "place")
    private String place;
}
