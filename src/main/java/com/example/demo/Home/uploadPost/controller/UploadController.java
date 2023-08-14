package com.example.demo.Home.uploadPost.controller;

import com.example.demo.Home.uploadPost.repository.RecipeRepository;
import com.example.demo.Home.uploadPost.service.UploadService;
import com.example.demo.Home.uploadPost.util.UploadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {
//    @Autowired
//    private UploadService uploadService;
//    private void errorManager(String value, String errorMessage){
//        if(value.isEmpty()){ throw new IllegalArgumentException(errorMessage); }
//    }
//    @PostMapping
//    public List<RecipeRepository.RecipeProjection> upLoadData(
//            @RequestParam(value = "textData", required = false) String[] textData,
//            @RequestParam(value = "pictureData", required = false) MultipartFile[] pictureData) throws JsonProcessingException {
//        if (textData == null && pictureData == null) {
//            errorManager("", "사진 또는 텍스트 중 하나 이상을 입력하세요.");
//        } else if (!UploadUtil.isImageFile(pictureData)) {
//            errorManager("", "사진 파일의 형식은 JPG, JPEG, HEIF, PNG 만 지원됩니다.");
//        } else {
//            return uploadService.handleUploadAndProcess(textData, pictureData);
//        }
//        return null;
//    }
}
