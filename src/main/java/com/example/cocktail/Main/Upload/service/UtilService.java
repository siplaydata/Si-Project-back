package com.example.cocktail.Main.UploadPost.service;

import com.example.cocktail.Main.UploadPost.dto.RecipeResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UtilService {
    List<String> sendToFlaskServer(List<MultipartFile> pictureData);

    List<MultipartFile> removeDuplicatePictureData(List<MultipartFile> pictureData);

    List<String> removeDuplicateTextData(List<String> textData);

    List<RecipeResponseDTO> getRecipes(List<String> ingredientsList);
}
