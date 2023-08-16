package com.example.cocktail.Home.UploadPost.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

