package com.example.cocktail.Main.Upload.service;

import com.example.cocktail.Main.Upload.dto.RecipeResponseDTO;
import com.example.cocktail.Main.Upload.repository.IngredientRepository;
import com.example.cocktail.Main.Upload.repository.PairRepository;
import com.example.cocktail.Main.Upload.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
        List<String> setIngredientList = utilService.removeDuplicateTextData(textData);
        return  utilService.getRecipes(setIngredientList);
    }

    private List<RecipeResponseDTO> onlyPictureData(List<MultipartFile> pictureData) {
        List<MultipartFile> uniquePictureData = utilService.removeDuplicatePictureData(pictureData);
        List<String> responseData = utilService.sendToFlaskServer(uniquePictureData);
        List<String> setIngredientList = utilService.removeDuplicateTextData(responseData);

        return utilService.getRecipes(setIngredientList);
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