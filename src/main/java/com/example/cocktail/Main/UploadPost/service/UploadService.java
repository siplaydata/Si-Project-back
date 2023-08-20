package com.example.cocktail.Main.UploadPost.service;

import com.example.cocktail.Main.UploadPost.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UploadService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private UtilService utilService;
    private List<String> withTextPictureData(List<String> textData, List<MultipartFile> pictureData) {
        return null;
    }
    private List<String> onlyTextData(List<String> textData) {
        return utilService.removeDuplicateTextData(textData);
    }
    private List<String> onlyPictureData(List<MultipartFile> pictureData) {
        List<MultipartFile> uniquePictureData = utilService.removeDuplicatePictureData(pictureData);
        List<String> responseData = utilService.sendToFlaskServer(uniquePictureData);
        List<String> setIngredient = utilService.removeDuplicateTextData(responseData);


        return setIngredient;
        // TODO : setResponseData 로 레포지토리에서 찾아서 return;
    }
    public List<String> handleUploadProcess(List<String> textData, List<MultipartFile> pictureData) {
        try {
            if (textData != null && pictureData != null) { return withTextPictureData(textData, pictureData); }
            if (textData != null) { return onlyTextData(textData); }
            if (pictureData != null){ return onlyPictureData(pictureData); }
        } catch (Exception e) {
            throw new RuntimeException("서버 장애 발생 에러내용 : " + e.getMessage());
        }
        return null;
    }
}

//    long startTime = System.nanoTime(); // 현재 시간 기록
//    long endTime = System.nanoTime(); // 현재 시간 기록
//    long elapsedTimeNano = endTime - startTime; // 실행 시간 계산
//    double elapsedTimeSeconds = (double) elapsedTimeNano / 1_000_000_000; // 나노초를 초로 변환
//        System.out.println("pictureByData Time : " + elapsedTimeSeconds);