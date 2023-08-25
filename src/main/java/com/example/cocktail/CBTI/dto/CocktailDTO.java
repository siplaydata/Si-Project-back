package com.example.cocktail.CBTI.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class CBTIResponseDTO {
    private int id;
    private String cocktailName;
    private String imageUrl;

    private String level;
    private String taste;
    private String place;
}
