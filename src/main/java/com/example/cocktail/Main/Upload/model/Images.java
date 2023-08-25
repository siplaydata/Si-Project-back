package com.example.cocktail.Main.Upload.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "testimages")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnum")
    private Long cnum;

    @Column(name = "pic")
    private byte[] picture;
}
