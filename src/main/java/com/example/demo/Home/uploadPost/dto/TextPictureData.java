package com.example.demo.Home.uploadPost.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TextPictureData {
    private String[] textData;
    private MultipartFile[] PictureData;
}
