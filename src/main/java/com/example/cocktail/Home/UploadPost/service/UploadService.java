package com.example.cocktail.Home.UploadPost.service;


import org.springframework.stereotype.Service;

@Service
public class UploadService {
//    @Autowired
//    private RecipeRepository recipeRepository;
//    @Autowired
//    private FlaskApi flaskApi;
//    private List<RecipeRepository.RecipeProjection> findByTextAndModel(String[] textData, MultipartFile[] pictureData) throws JsonProcessingException {
//        long startTime = System.nanoTime(); // 시작 시간 기록
//
//        List<String> ingredientsNumFromText = recipeRepository.findDistinctIngredientNumByIngredientsIn(new HashSet<>(List.of(textData)));
//        Set<String> ingredientsNumFromPicture = UploadUtil.createObjectMapper(flaskApi.sendToFlaskServer(pictureData));
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
//        return recipeRepository.findByIngredientNumIn(new ArrayList<>(UploadUtil.createObjectMapper(flaskApi.sendToFlaskServer(pictureData))));
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
}