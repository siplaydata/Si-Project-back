package com.example.cocktail.Main.UploadPost.controller;

import com.example.cocktail.Main.UploadPost.service.UploadService;
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
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    private static final int MAX_NUMBER_OF_PICTURE = 10;
    private void validateTextAndPictureData(List<String> textData, List<MultipartFile> pictureData) {
        if ((textData == null || textData.isEmpty()) && (pictureData == null || pictureData.isEmpty())) {
            throw new IllegalArgumentException("사진 또는 텍스트 중 하나 이상을 입력하세요.");
        }
        if (pictureData != null && !pictureData.isEmpty()) {
            if (pictureData.size() > MAX_NUMBER_OF_PICTURE) {
                throw new IllegalArgumentException("사진은 최대 3장만 전송할 수 있습니다.");
            }
            for (MultipartFile file : pictureData) {
                if (file.isEmpty()) {
                    throw new IllegalArgumentException("업로드된 사진이 없습니다.");
                }
                if (file.getSize() > MAX_FILE_SIZE) {
                    throw new IllegalArgumentException("사진의 파일 크기는 5MB를 초과할 수 없습니다.");
                }
                String fileName = file.getOriginalFilename();
                String lowerCaseFileName = Objects.requireNonNull(fileName).toLowerCase();
                if (!lowerCaseFileName.endsWith(".jpg") &&
                        !lowerCaseFileName.endsWith(".jpeg") &&
                        !lowerCaseFileName.endsWith(".png") &&
                        !lowerCaseFileName.endsWith(".heif")) {
                    throw new IllegalArgumentException("사진 파일의 형식은 JPG, JPEG, PNG, HEIF 만 지원됩니다.");
                }
            }
        }
    }
    @PostMapping
    public List<String> uploadData(@RequestParam(value = "textData", required = false) List<String> textData,
                              @RequestParam(value = "pictureData", required = false) List<MultipartFile> pictureData
    ) {
        validateTextAndPictureData(textData, pictureData);

        return uploadService.handleUploadProcess(textData, pictureData);
    }
}