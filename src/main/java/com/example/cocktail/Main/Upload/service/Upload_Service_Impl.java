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
import com.example.cocktail.Main.Upload.repository.Recipe_Ingredient.Recipe_Ingredient_Repository;
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

    @Override
    public List<RecipeDTO> sendToFlaskServer(MultipartFile pictureData) {
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
                        return getRecipes(responseDataList.get(0));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("flask 서버와 통신 실패", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("HTTP 클라이언트 생성 실패", e);
        }
        return null;
    }
    public List<RecipeDTO> getRecipes(String alcohol){
        List<Alcohol> findAlcohols = findAlcohols(alcohol);
        List<Ingredient> ingredients = findIngredients(findAlcohols);
        List<Recipe_Ingredient> findRecipeIngredients = findRecipeIngredients(ingredients);

        return buildRecipeDTOs(findAlcohols, ingredients, findRecipeIngredients, alcohol);
    }

    private List<Alcohol> findAlcohols(String alcohol) {
        if (alcohol.chars().anyMatch(c -> c >= '가' && c <= '힣')) {
            return alcoholRepository.findByKoreanAlcoholContainingIgnoreCase(alcohol);
        } else {
            return alcoholRepository.findByEnglishAlcoholContainingIgnoreCase(alcohol);
        }
    }

    private List<Ingredient> findIngredients(List<Alcohol> alcohols) {
        return alcohols.stream()
                .flatMap(alcoholObj -> {
                    List<Ingredient_Alcohol> ingredientAlcohols = ingredientAlcoholRepository.findByAlcoholId(alcoholObj.getId());
                    return ingredientAlcohols.stream()
                            .map(ingredientAlcohol -> ingredientRepository.findById(ingredientAlcohol.getIngredientId()).orElse(null))
                            .filter(Objects::nonNull);
                })
                .collect(Collectors.toList());
    }

    private List<Recipe_Ingredient> findRecipeIngredients(List<Ingredient> ingredients) {
        return ingredients.stream()
                .flatMap(ingredient -> recipeIngredientRepository.findByIngredientId(ingredient.getId()).stream())
                .collect(Collectors.toList());
    }
    private List<RecipeDTO> buildRecipeDTOs(List<Alcohol> alcohols, List<Ingredient> ingredients,
                                            List<Recipe_Ingredient> recipeIngredients, String alcohol) {
        List<RecipeDTO> recipeDTOs = new ArrayList<>();

        recipeIngredients.forEach(recipeIngredient -> {
            Recipe recipe = recipeRepository.findById(recipeIngredient.getRecipeId()).orElse(null);

            if (recipe != null) {
                Alcohol Alcohol = alcohols.stream().findFirst().orElse(null);
                Ingredient Ingredient = ingredients.isEmpty() ? null : ingredients.get(0);

                RecipeDTO recipeDTO = new RecipeDTO(
                        recipe.getId(),
                        alcohol,

                        Alcohol != null ? Alcohol.getKoreanAlcohol() : null,
                        Alcohol != null ? Alcohol.getEnglishAlcohol() : null,

                        Ingredient != null ? Ingredient.getKoreanIngredient() : null,
                        Ingredient != null ? Ingredient.getEnglishIngredient() : null,

                        recipe.getCocktail().getKoreanName(),
                        recipe.getCocktail().getEnglishName(),

                        recipe.getIngredients(),
                        recipe.getMethod(),
                        recipe.getGarnish(),
                        recipe.getImages().getPicture()
                );
                recipeDTOs.add(recipeDTO);
            }
        });
        return recipeDTOs;
    }
}