package com.example.cocktail.Main.Upload.service;

import com.example.cocktail.Main.Upload.dto.RecipeResponseDTO;
import com.example.cocktail.Main.Upload.model.Images;
import com.example.cocktail.Main.Upload.model.Ingredient;
import com.example.cocktail.Main.Upload.model.Pair;
import com.example.cocktail.Main.Upload.model.Recipe;
import com.example.cocktail.Main.Upload.repository.ImagesRepository;
import com.example.cocktail.Main.Upload.repository.IngredientRepository;
import com.example.cocktail.Main.Upload.repository.PairRepository;
import com.example.cocktail.Main.Upload.repository.RecipeRepository;
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

@Service
public class UtilServiceImpl implements UtilService {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PairRepository pairRepository;
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private ImagesRepository imagesRepository;
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
//    @Override
//    public List<RecipeResponseDTO> getRecipes(List<String> ingredientsList) {
//        Map<Long, List<String>> ingredientToIngredientsListMap = new HashMap<>();
//        Map<Long, Recipe> recipeMap = ingredientsList.stream()
//                .flatMap(ingredient -> ingredientRepository.findByIngredientEnglishContainingIgnoreCase(ingredient).stream()
//                        .peek(foundIngredient -> {
//                            Long inum = foundIngredient.getInum();
//                            List<String> inputIngredientsList = ingredientToIngredientsListMap.getOrDefault(inum, new ArrayList<>());
//                            inputIngredientsList.add(ingredient);
//                            ingredientToIngredientsListMap.put(inum, inputIngredientsList);
//                        }))
//                .map(Ingredient::getInum)
//                .flatMap(inum -> pairRepository.findByInum(inum).stream())
//                .map(Pair::getCnum)
//                .distinct()
//                .collect(Collectors.toMap(
//                        cnum -> cnum,
//                        cnum -> recipeRepository.findById(cnum).orElse(null)
//                ));
//
//        List<RecipeResponseDTO> responseDTOs = new ArrayList<>();
//
//        for (Recipe recipe : recipeMap.values()) {
//            RecipeResponseDTO responseDTO = new RecipeResponseDTO();
//            responseDTO.setIngredientType(recipe.getPairs().stream()
//                    .map(pair -> pair.getIngredient().getKingre())
//                    .distinct()
//                    .collect(Collectors.joining(", ")));
//
//            List<String> inputValues = ingredientToIngredientsListMap.get(recipe.getPairs().get(0).getInum());
//
//            responseDTO.setUserInputData(String.join(", ", inputValues));
//            responseDTO.setName(recipe.getName());
//            responseDTO.setIngredients(recipe.getIngredients());
//            responseDTO.setCocktailMethod(recipe.getCocktailMethod());
//            responseDTO.setGarnish(recipe.getGarnish());
//
//            Images image = imagesRepository.findByCnum(recipe.getCnum());
//            if (image != null) { responseDTO.setImage(image.getPicture()); }
//            responseDTOs.add(responseDTO);
//        }
//        return responseDTOs;
//    }
    @Override
    public List<RecipeResponseDTO> getRecipes(List<String> ingredientsList) {
        Map<Long, List<String>> ingredientToIngredientsListMap = new HashMap<>();
        Map<Long, Recipe> recipeMap = buildRecipeMap(ingredientsList, ingredientToIngredientsListMap);

        List<RecipeResponseDTO> responseDTOs = new ArrayList<>();
        for (Recipe recipe : recipeMap.values()) {
            RecipeResponseDTO responseDTO = createRecipeResponseDTO(recipe, ingredientToIngredientsListMap);
            responseDTOs.add(responseDTO);
        }
        System.out.println("------------ responseDTOs size : " + responseDTOs.size() + " ------------");
        return responseDTOs;
    }

    private Map<Long, Recipe> buildRecipeMap(List<String> ingredientsList, Map<Long, List<String>> ingredientToIngredientsListMap) {
        return ingredientsList.stream()
                .flatMap(ingredient -> ingredientRepository.findByIngredientEnglishContainingIgnoreCase(ingredient).stream()
                        .peek(foundIngredient -> {
                            Long inum = foundIngredient.getInum();
                            ingredientToIngredientsListMap
                                    .computeIfAbsent(inum, key -> new ArrayList<>())
                                    .add(ingredient);
                        }))
                .map(Ingredient::getInum)
                .flatMap(inum -> pairRepository.findByInum(inum).stream())
                .map(Pair::getCnum)
                .distinct()
                .collect(Collectors.toMap(
                        cnum -> cnum,
                        cnum -> recipeRepository.findById(cnum).orElse(null)
                ));
    }

    private RecipeResponseDTO createRecipeResponseDTO(Recipe recipe, Map<Long, List<String>> ingredientToIngredientsListMap) {
        RecipeResponseDTO responseDTO = new RecipeResponseDTO();

        List<String> inputValues = ingredientToIngredientsListMap.get(recipe.getPairs().get(0).getInum());
        String ingredientType = recipe.getPairs().stream()
                .map(pair -> pair.getIngredient().getKingre())
                .distinct()
                .collect(Collectors.joining(", "));

        responseDTO.setUserInputData(String.join(", ", inputValues));
        responseDTO.setCocktailName(recipe.getName());
        responseDTO.setIngredientType(ingredientType);
        responseDTO.setIngredients(recipe.getIngredients());
        responseDTO.setCocktailMethod(recipe.getCocktailMethod());
        responseDTO.setGarnish(recipe.getGarnish());

        Images image = imagesRepository.findByCnum(recipe.getCnum());
        if (image != null) {
            responseDTO.setImage(image.getPicture());
        }
        return responseDTO;
    }
}
