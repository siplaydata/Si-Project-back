package com.example.cocktail.Main.Upload.controller;

import com.example.cocktail.Main.Upload.dto.RecipeDTO;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import com.example.cocktail.Main.Upload.model.Recipe.Recipe;
import com.example.cocktail.Main.Upload.service.Upload_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
public class Upload_Controller {
    @Autowired
    private Upload_Service uploadService;
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private void validateTextAndPictureData(MultipartFile pictureData) {
        if (pictureData == null || pictureData.isEmpty()) {
            throw new IllegalArgumentException("업로드된 사진이 없습니다.");
        }
        if (pictureData.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("사진의 파일 크기는 5MB를 초과할 수 없습니다.");
        }
        String fileName = pictureData.getOriginalFilename();
        String lowerCaseFileName = Objects.requireNonNull(fileName).toLowerCase();
        if (!lowerCaseFileName.endsWith(".jpg") &&
                !lowerCaseFileName.endsWith(".jpeg") &&
                !lowerCaseFileName.endsWith(".png") &&
                !lowerCaseFileName.endsWith(".heif")) {
            throw new IllegalArgumentException("사진 파일의 형식은 JPG, JPEG, PNG, HEIF 만 지원됩니다.");
        }
    }

    @PostMapping
    public List<RecipeDTO> uploadData(MultipartFile pictureData) {
        validateTextAndPictureData(pictureData);
        return uploadService.sendToFlaskServer(pictureData);
    }
}