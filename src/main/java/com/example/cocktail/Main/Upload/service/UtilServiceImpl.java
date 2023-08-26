package com.example.cocktail.Main.Upload.service;

import com.example.cocktail.Main.Upload.dto.RecipeResponseDTO;
import com.example.cocktail.Main.Upload.model.*;
import com.example.cocktail.Main.Upload.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.stream.Stream;

@Service
public class UtilServiceImpl implements UtilService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private CocktailRepository cocktailRepository;
    @Value("${flask.server}")
    private String flaskServer;
    @Override
    public List<String> sendToFlaskServer(List<MultipartFile> pictureData) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(flaskServer);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            for (MultipartFile file : pictureData) {
                builder.addBinaryBody(
                        "images",                      // 파라미터 이름
                        file.getBytes(),               // 바이너리 데이터
                        ContentType.APPLICATION_OCTET_STREAM,  // 컨텐츠 타입
                        file.getOriginalFilename()     // 파일 이름
                );
            }
            HttpEntity multipart = builder.build();

            httpPost.setEntity(multipart);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    String responseBody = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                    // JSON 파싱
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(responseBody, new TypeReference<List<String>>() {});
                }
            } catch (Exception e) {
                throw new RuntimeException("Flask 서버와 통신 실패", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("HTTP 클라이언트 생성 실패", e);
        }
        return null;
    }

    @Override
    public List<MultipartFile> removeDuplicatePictureData(List<MultipartFile> pictureData) {
        return pictureData.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<String> removeDuplicateTextData(List<String> textData) {
        return textData.stream()
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<RecipeResponseDTO> getRecipes(List<String> ingredientsList) {
        Map<Integer, List<String>> ingredientToIngredientsListMap = new HashMap<>();
        Map<Integer, Recipe> recipeMap = buildRecipeMap(ingredientsList, ingredientToIngredientsListMap);

        List<RecipeResponseDTO> responseDTOs = new ArrayList<>();
        for (Recipe recipe : recipeMap.values()) {
            RecipeResponseDTO responseDTO = createRecipeResponseDTO(recipe, ingredientToIngredientsListMap);
            responseDTOs.add(responseDTO);
        }
        return responseDTOs;
    }
    private boolean isKorean(String text) { return text.chars().anyMatch(c -> c >= '가' && c <= '힣'); }
    private Map<Integer, Recipe> buildRecipeMap(List<String> ingredientsList, Map<Integer, List<String>> ingredientToIngredientsListMap) {
        return ingredientsList.stream()
                .flatMap(ingredient -> {
                    Stream<Ingredient> ingredientStream;

                    if (isKorean(ingredient)) {
                        ingredientStream = ingredientRepository.findByKoreanAlcoholContainingIgnoreCase(ingredient).stream();
                    } else {
                        ingredientStream = ingredientRepository.findByEnglishAlcoholContainingIgnoreCase(ingredient).stream();
                    }
                    return ingredientStream

                        .peek(foundIngredient -> {
                            int ingredientId = foundIngredient.getId();
                            ingredientToIngredientsListMap
                                    .computeIfAbsent(ingredientId, key -> new ArrayList<>())
                                    .add(ingredient);
                        });
                })
                .map(Ingredient::getId)
                .flatMap(ingredientId -> recipeIngredientRepository.findByIngredientId(ingredientId).stream())
                .map(RecipeIngredient::getRecipeId)
                .distinct()
                .collect(Collectors.toMap(
                        recipeId -> recipeId,
                        recipeId -> recipeRepository.findById(recipeId).orElse(new Recipe())
                ));
    }

    private RecipeResponseDTO createRecipeResponseDTO(Recipe recipe, Map<Integer, List<String>> ingredientToIngredientsListMap) {
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();

        List<String> inputValues = ingredientToIngredientsListMap.get(recipe.getRecipeIngredients().get(0).getIngredientId());
        String ingredientType = recipe.getRecipeIngredients().stream()
                .map(recipeIngredient -> recipeIngredient.getIngredient().getKoreanIngredient())
                .distinct()
                .collect(Collectors.joining(", "));

        Cocktail cocktail = cocktailRepository.findById(recipe.getId());

        responseDTO.setUserInputData(String.join(", ", inputValues));
        responseDTO.setCocktailKorean(cocktail.getKoreanName());
        responseDTO.setCocktailEnglish(cocktail.getEnglishName());
//        responseDTO.setCocktailKorean(recipe.getCocktail().getKoreanName());
//        responseDTO.setCocktailEnglish(recipe.getCocktail().getEnglishName());
        responseDTO.setIngredientType(ingredientType);
        responseDTO.setIngredients(recipe.getIngredients());
        responseDTO.setCocktailMethod(recipe.getMethod());
        responseDTO.setGarnish(recipe.getGarnish());

        responseDTO.setImage(imagesRepository.findById(recipe.getId()).getPicture());
//        responseDTO.setImage(recipe.getImages().getPicture());
        return responseDTO;
    }
}