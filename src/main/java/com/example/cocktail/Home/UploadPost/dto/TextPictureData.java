package com.example.cocktail.Home.UploadPost.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TextPictureData {
    private String[] textData;
    private MultipartFile[] PictureData;
}
