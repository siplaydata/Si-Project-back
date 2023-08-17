package com.example.cocktail.Home.UploadPost.controller;

import com.example.cocktail.Home.UploadPost.dto.TextPictureDataDTO;
import com.example.cocktail.Home.UploadPost.model.Recipe;
import com.example.cocktail.Home.UploadPost.repository.RecipeRepository;
import com.example.cocktail.Home.UploadPost.service.UploadService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    private boolean isImageFile(MultipartFile[] pictureData) {
        if (pictureData == null) { return true; }

        for (MultipartFile file : pictureData) {
            String fileName = file.getOriginalFilename();

            String lowerCaseFileName = Objects.requireNonNull(fileName).toLowerCase();
            if (!lowerCaseFileName.endsWith(".jpg") &&
                    !lowerCaseFileName.endsWith(".jpeg") &&
                    !lowerCaseFileName.endsWith(".png") &&
                    !lowerCaseFileName.endsWith(".heif")) {
                return false;
            }
        }
        return true;
    }
    private void errorManager(String[] textData, MultipartFile[] pictureData) {
        if (textData == null && pictureData == null) {
            throw new IllegalArgumentException("사진 또는 텍스트 중 하나 이상을 입력하세요."); }
        if (pictureData != null) {
            if (!isImageFile(pictureData)) {
                throw new IllegalArgumentException("사진 파일의 형식은 JPG, JPEG, HEIF, PNG 만 지원됩니다.");
            }
        }
    }
    @PostMapping
    public List<Recipe> upLoadData (
            @RequestParam(value = "textData", required = false) String[]textData,
            @RequestParam(value = "pictureData", required = false) MultipartFile[]pictureData ) {
        errorManager(textData, pictureData);
        return uploadService.handleUploadAndProcess(textData, pictureData);
    }
}