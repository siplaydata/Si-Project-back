package com.example.cocktail.Main.UploadPost.service;

import com.example.cocktail.Main.UploadPost.dto.RecipeResponseDTO;
import com.example.cocktail.Main.UploadPost.model.Images;
import com.example.cocktail.Main.UploadPost.model.Ingredient;
import com.example.cocktail.Main.UploadPost.model.Pair;
import com.example.cocktail.Main.UploadPost.model.Recipe;
import com.example.cocktail.Main.UploadPost.repository.ImagesRepository;
import com.example.cocktail.Main.UploadPost.repository.IngredientRepository;
import com.example.cocktail.Main.UploadPost.repository.PairRepository;
import com.example.cocktail.Main.UploadPost.repository.RecipeRepository;
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
//        Map<Long, Recipe> recipeMap = ingredientsList.stream()
//                .flatMap(ingredient -> ingredientRepository.findByIngredientEnglishContainingIgnoreCase(ingredient).stream())
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
//            List<String> matchedIngredientEnglishList = recipe.getPairs().stream()
//                    .map(pair -> pair.getIngredient().getIngredientEnglish())
//                    .filter(ingredientsList::contains)
//                    .distinct()
//                    .collect(Collectors.toList());
//
//            RecipeResponseDTO responseDTO = new RecipeResponseDTO();
//            responseDTO.setKingre(recipe.getPairs().stream()
//                    .map(pair -> pair.getIngredient().getKingre())
//                    .distinct()
//                    .collect(Collectors.toList()));
//
//            responseDTO.setInputData(matchedIngredientEnglishList);
//
//            // Find the ingredientEnglish values that matched the inputData and set them
//            List<String> inputDataMatchedValues = ingredientsList.stream()
//                    .filter(matchedIngredientEnglishList::contains)
//                    .collect(Collectors.toList());
//            responseDTO.setInputData(inputDataMatchedValues);
//
//            responseDTO.setName(recipe.getName());
//            responseDTO.setIngredients(recipe.getIngredients());
//            responseDTO.setCocktailMethod(recipe.getCocktailMethod());
//            responseDTO.setGarnish(recipe.getGarnish());
//
//            Images image = imagesRepository.findByCnum(recipe.getCnum());
//            if (image != null) {
//                responseDTO.setImage(image.getPicture());
//            }
//
//            responseDTOs.add(responseDTO);
//        }
//
//        return responseDTOs;
//    }

    @Override
    public List<RecipeResponseDTO> getRecipes(List<String> ingredientsList) {
        Map<Long, List<String>> ingredientToIngredientsListMap = new HashMap<>();
        Map<Long, Recipe> recipeMap = ingredientsList.stream()
                .flatMap(ingredient -> ingredientRepository.findByIngredientEnglishContainingIgnoreCase(ingredient).stream()
                        .peek(foundIngredient -> {
                            Long inum = foundIngredient.getInum();
                            List<String> inputIngredientsList = ingredientToIngredientsListMap.getOrDefault(inum, new ArrayList<>());
                            inputIngredientsList.add(ingredient);
                            ingredientToIngredientsListMap.put(inum, inputIngredientsList);
                        }))
                .map(Ingredient::getInum)
                .flatMap(inum -> pairRepository.findByInum(inum).stream())
                .map(Pair::getCnum)
                .distinct()
                .collect(Collectors.toMap(
                        cnum -> cnum,
                        cnum -> recipeRepository.findById(cnum).orElse(null)
                ));

        List<RecipeResponseDTO> responseDTOs = new ArrayList<>();

        for (Recipe recipe : recipeMap.values()) {
//            List<String> matchedIngredientEnglishList = recipe.getPairs().stream()
//                    .map(pair -> pair.getIngredient().getIngredientEnglish())
//                    .filter(ingredientsList::contains)
//                    .distinct()
//                    .collect(Collectors.toList());

            RecipeResponseDTO responseDTO = new RecipeResponseDTO();
            responseDTO.setIngredientType(String.join(", ", recipe.getPairs().stream()
                    .map(pair -> pair.getIngredient().getKingre())
                    .distinct()
                    .collect(Collectors.toList())));

            // Get the input values used for this recipe and set them in inputData
            List<String> inputValues = ingredientToIngredientsListMap.get(recipe.getPairs().get(0).getInum());
            responseDTO.setUserInputData(String.join(", ", inputValues));
            responseDTO.setName(recipe.getName());
            responseDTO.setIngredients(recipe.getIngredients());
            responseDTO.setCocktailMethod(recipe.getCocktailMethod());
            responseDTO.setGarnish(recipe.getGarnish());

            Images image = imagesRepository.findByCnum(recipe.getCnum());
            if (image != null) {
                responseDTO.setImage(image.getPicture());
            }

            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }
}
