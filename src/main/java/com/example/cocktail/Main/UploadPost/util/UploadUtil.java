//package com.example.cocktail.Home.UploadPost.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//public class UploadUtil {
//
////    public static boolean isImageFile(MultipartFile[] pictureData) {
////        if (pictureData == null) {
////            return true;
////        }
////        for (MultipartFile file : pictureData) {
////            String fileName = file.getOriginalFilename();
////
////            String lowerCaseFileName = Objects.requireNonNull(fileName).toLowerCase();
////            if (!lowerCaseFileName.endsWith(".jpg") &&
////                    !lowerCaseFileName.endsWith(".jpeg") &&
////                    !lowerCaseFileName.endsWith(".png") &&
////                    !lowerCaseFileName.endsWith(".heif")) {
////                return false;
////            }
////        }
////        return true;
////    }
//    public static Set<String> createObjectMapper(String modelResults) throws JsonProcessingException {
//        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
//
//        JsonNode getResult = objectMapper.readTree(modelResults);
//
//        Set<String> ingredientValues = new HashSet<>();
//
//        for (JsonNode node : getResult) {
//            ingredientValues.add(node.asText());
//        }
//        return ingredientValues;
//      }
//}
//
