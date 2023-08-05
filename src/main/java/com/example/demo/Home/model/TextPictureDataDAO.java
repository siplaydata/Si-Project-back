package com.example.demo.Home.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TextPictureDataDAO {
    private String text1;
    private String text2;
    private String text3;
    private MultipartFile picture1;
    private MultipartFile picture2;
    private MultipartFile picture3;
}
