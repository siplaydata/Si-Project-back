package com.example.cocktail.Home.UploadPost.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "alcohol_names")
public class Recipe {
    @Id
    private String id;

    private String name;

    private String category;
}
