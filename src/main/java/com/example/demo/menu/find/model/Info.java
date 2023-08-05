package com.example.demo.menu.find.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Info {
    @Id
    @Column
    private String name;

    @Column
    private String picture;
}
