package com.example.cocktail.Main.Upload.service;

import com.example.cocktail.Main.Upload.dto.RecipeDTO;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Alcohol;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Ingredient;
import com.example.cocktail.Main.Upload.model.Ingredient_Alcohol.Ingredient_Alcohol;
import com.example.cocktail.Main.Upload.model.Recipe.Recipe;
import com.example.cocktail.Main.Upload.model.Recipe_Ingredient.Recipe_Ingredient;
import com.example.cocktail.Main.Upload.repository.Ingredient_Alcohol.Alcohol_Repository;
import com.example.cocktail.Main.Upload.repository.Ingredient_Alcohol.Ingredient_Alcohol_Repository;
import com.example.cocktail.Main.Upload.repository.Ingredient_Alcohol.Ingredient_Repository;
import com.example.cocktail.Main.Upload.repository.Recipe.Recipe_Repository;
import com.example.cocktail.Main.Upload.repository.Recipe_Ingrecient.Recipe_Ingredient_Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Upload_Service_Impl implements Upload_Service {
    @Autowired
    private Alcohol_Repository alcoholRepository;
    @Autowired
    private Ingredient_Repository ingredientRepository;
    @Autowired
    private Ingredient_Alcohol_Repository ingredientAlcoholRepository;
    @Autowired
    private Recipe_Ingredient_Repository recipeIngredientRepository;
    @Autowired
    private Recipe_Repository recipeRepository;
    @Value("${flask.server}")
    private String flaskServer;

    private String sendToFlaskServer(MultipartFile pictureData) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(flaskServer);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.addBinaryBody(
                    "images",                      // 파라미터 이름
                    pictureData.getBytes(),               // 바이너리 데이터
                    ContentType.APPLICATION_OCTET_STREAM,  // 컨텐츠 타입
                    pictureData.getOriginalFilename()     // 파일 이름
            );

            HttpEntity multipart = builder.build();

            httpPost.setEntity(multipart);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);

                    ObjectMapper objectMapper = new ObjectMapper();
                    List<String> responseDataList = objectMapper.readValue(responseBody, new TypeReference<List<String>>() {
                    });

                    if (!responseDataList.isEmpty()) {
                        return responseDataList.get(0);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Flask 서버와 통신 실패", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("HTTP 클라이언트 생성 실패", e);
        }
        return null;
    }

    private boolean isKorean(String text) { return text.chars().anyMatch(c -> c >= '가' && c <= '힣'); }

    @Override
    public List<RecipeDTO> getRecipes(MultipartFile pictureData) {
        String alcohol = sendToFlaskServer(pictureData);
        if (alcohol == null) { return null; }

        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        Set<Alcohol> findAlcoholId = new HashSet<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Recipe_Ingredient> findRecipeIngredients = new ArrayList<>();

        if (isKorean(alcohol)) {
            findAlcoholId.addAll(alcoholRepository.findByKoreanAlcoholContainingIgnoreCase(alcohol));
        } else {
            findAlcoholId.addAll(alcoholRepository.findByEnglishAlcoholContainingIgnoreCase(alcohol));
        }

        findAlcoholId.forEach(alcoholObj -> {
            List<Ingredient_Alcohol> ingredientAlcohols = ingredientAlcoholRepository.findByAlcoholId(alcoholObj.getId());
            List<Ingredient> alcoholIngredients = ingredientAlcohols.stream()
                    .map(ingredientAlcohol -> ingredientRepository.findById(ingredientAlcohol.getIngredientId()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            ingredients.addAll(alcoholIngredients);
        });

        ingredients.forEach(ingredient -> {
            List<Recipe_Ingredient> recipeIngredients = recipeIngredientRepository.findByIngredientId(ingredient.getId());
            findRecipeIngredients.addAll(recipeIngredients);
        });

        findRecipeIngredients.forEach(recipeIngredient -> {
            Recipe recipe = recipeRepository.findById(recipeIngredient.getRecipeId()).orElse(null);
            if (recipe != null) {
                RecipeDTO recipeDTO = new RecipeDTO();
                recipeDTO.setId(recipe.getId());
                recipeDTO.setUserInputData(alcohol);
                recipeDTO.setKoreanAlcohol(findAlcoholId.stream().findFirst().map(Alcohol::getKoreanAlcohol).orElse(null));
                recipeDTO.setEnglishAlcohol(findAlcoholId.stream().findFirst().map(Alcohol::getEnglishAlcohol).orElse(null));


                if (!ingredients.isEmpty()) {
                    Ingredient firstIngredient = ingredients.get(0);
                    recipeDTO.setKoreanIngredient(firstIngredient.getKoreanIngredient());
                    recipeDTO.setEnglishIngredient(firstIngredient.getEnglishIngredient());
                }
                recipeDTO.setKoreanCocktailName(recipe.getCocktail().getKoreanName());
                recipeDTO.setEnglishCocktailName(recipe.getCocktail().getEnglishName());

                recipeDTO.setIngredients(recipe.getIngredients());
                recipeDTO.setMethod(recipe.getMethod());
                recipeDTO.setGarnish(recipe.getGarnish());

                recipeDTO.setImage(recipe.getImages().getPicture());

                recipeDTOs.add(recipeDTO);
            }
        });
        System.out.println("results : " + recipeDTOs.size());
        return recipeDTOs;
    }
}