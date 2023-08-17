package com.example.cocktail.Home.UploadPost.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TextPictureDataDTO {
    private String[] textData;
    private MultipartFile[] pictureData;
}
