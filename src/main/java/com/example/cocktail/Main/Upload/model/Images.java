package com.example.cocktail.Main.UploadPost.model;

import lombok.Data;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnum")
    private Long cnum;

    @Column(name = "pic")
    private byte[] picture;
}
