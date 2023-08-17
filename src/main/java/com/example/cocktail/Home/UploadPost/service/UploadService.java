package com.example.cocktail.Home.UploadPost.service;


//import com.example.cocktail.Component.API.FlaskAPI;
//import com.example.cocktail.Home.UploadPost.repository.RecipeRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UploadService {
//    @Autowired
//    private RecipeRepository recipeRepository;
//    @Autowired
//    private FlaskAPI flaskAPI;
//    private List<RecipeRepository.RecipeProjection> findByTextAndModel(String[] textData, MultipartFile[] pictureData) throws JsonProcessingException {
//        long startTime = System.nanoTime(); // 시작 시간 기록
//
//        List<String> ingredientsNumFromText = recipeRepository.findDistinctIngredientNumByIngredientsIn(new HashSet<>(List.of(textData)));
//        Set<String> ingredientsNumFromPicture = UploadUtil.createObjectMapper(flaskAPI.sendToFlaskServer(pictureData));
//
//        Set<String> combinedIngredientsNum = new HashSet<>();
//        combinedIngredientsNum.addAll(ingredientsNumFromText);
//        combinedIngredientsNum.addAll(ingredientsNumFromPicture);
//
//        long endTime = System.nanoTime(); // 종료 시간 기록
//        double durationInSeconds  = (endTime - startTime) / 1_000_000_000.0; // 나노초를 밀리초로 변환
//        System.out.println("cho : " + durationInSeconds  + "초 seconds");
//
//        return recipeRepository.findByIngredientNumIn(new ArrayList<>(combinedIngredientsNum));
//    }
//    private List<RecipeRepository.RecipeProjection> findByOnlyText(String[] textData) {
//        return recipeRepository.findByIngredientsIn(new ArrayList<>(List.of(textData)));
//    }
//    private List<RecipeRepository.RecipeProjection> findByOnlyPicture(MultipartFile[] pictureData) throws JsonProcessingException {
//        return recipeRepository.findByIngredientNumIn(new ArrayList<>(UploadUtil.createObjectMapper(flaskAPI.sendToFlaskServer(pictureData))));
//    }
//    public List<RecipeRepository.RecipeProjection> handleUploadAndProcess(String[] textData, MultipartFile[] pictureData) throws JsonProcessingException {
//        if (textData != null && pictureData != null) {
//            return findByTextAndModel(textData, pictureData);
//        } else if (textData != null) {
//            return findByOnlyText(textData);
//        } else {
//            return findByOnlyPicture(pictureData);
//        }
//    }
//}

import com.example.cocktail.Home.UploadPost.model.Recipe;
import com.example.cocktail.Home.UploadPost.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UploadService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UtilService utilService;

    public List<String> textByData(String[] textData) {
        return Arrays.stream(textData)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<MultipartFile> removeDuplicates(MultipartFile[] pictureData) {
        return List.of(pictureData).stream()
                .distinct()
                .collect(Collectors.toList());
    }
//    public List<Recipe> findRecipesByIds(List<String> ids) {
//        return recipeRepository.findByIdIn(ids);
//    }
    public List<Recipe> pictureByData(MultipartFile[] pictureData) {
        List<MultipartFile> uniquePicture = utilService.removeDuplicate(pictureData);
        List<String> results = utilService.sendToFlaskServer(uniquePicture);

//        List<Recipe> returnResults = findRecipesByIds(results);

        return recipeRepository.findByIdIn(results);
    }
    public List<Recipe> handleUploadAndProcess(String[] textData, MultipartFile[] pictureData) {
        if (textData != null && pictureData != null) {
            return null;
        }
        if (textData != null) {
//            return textByData(textData);
            return null;
        }
        if (pictureData != null){
            long startTime = System.nanoTime(); // 현재 시간 기록

            List<Recipe> results = pictureByData(pictureData);

            long endTime = System.nanoTime(); // 현재 시간 기록
            long elapsedTimeNano = endTime - startTime; // 실행 시간 계산
            double elapsedTimeSeconds = (double) elapsedTimeNano / 1_000_000_000; // 나노초를 초로 변환
            System.out.println("pictureByData Time : " + elapsedTimeSeconds);
            return results;
//            return pictureByData(pictureData);
        }
        throw new RuntimeException("비 정 상 적 인 접 근 입 니 다.");
    }
}