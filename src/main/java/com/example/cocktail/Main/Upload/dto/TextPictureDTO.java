package com.example.cocktail.Main.Upload.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class TextPictureDTO {
    private List<String> textData;
    private List<MultipartFile> pictureData;
}