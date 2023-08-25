package com.example.cocktail.Main.UploadPost.service;

import com.example.cocktail.Main.UploadPost.dto.RecipeResponseDTO;
import com.example.cocktail.Main.UploadPost.model.Ingredient;
import com.example.cocktail.Main.UploadPost.model.Pair;
import com.example.cocktail.Main.UploadPost.model.Recipe;
import com.example.cocktail.Main.UploadPost.repository.IngredientRepository;
import com.example.cocktail.Main.UploadPost.repository.PairRepository;
import com.example.cocktail.Main.UploadPost.repository.RecipeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UploadService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UtilService utilService;

    private List<RecipeResponseDTO> withTextPictureData(List<String> textData, List<MultipartFile> pictureData) {
        List<MultipartFile> uniquePictureData = utilService.removeDuplicatePictureData(pictureData);
        List<String> responseData = utilService.sendToFlaskServer(uniquePictureData);

        responseData.addAll(textData);

        List<String> uniqueResults = utilService.removeDuplicateTextData(responseData);
        return utilService.getRecipes(uniqueResults);
    }

    private List<RecipeResponseDTO> onlyTextData(List<String> textData) {
        List<String> uniqueTextData = utilService.removeDuplicateTextData(textData);
        return  utilService.getRecipes(uniqueTextData);
    }

    private List<RecipeResponseDTO> onlyPictureData(List<MultipartFile> pictureData) {
        List<MultipartFile> uniquePictureData = utilService.removeDuplicatePictureData(pictureData);
        List<String> responseData = utilService.sendToFlaskServer(uniquePictureData);
        List<String> uniqueResponseData = utilService.removeDuplicateTextData(responseData);

        return utilService.getRecipes(uniqueResponseData);
    }

    public List<RecipeResponseDTO> handleUploadProcess(List<String> textData, List<MultipartFile> pictureData) {
        try {
            if (textData != null && pictureData != null) {
                return withTextPictureData(textData, pictureData);
            }
            if (textData != null) {
                return onlyTextData(textData);
            }
            if (pictureData != null) {
                return onlyPictureData(pictureData);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("서버 장애 발생 에러내용 : " + e.getMessage());
        }
    }
}