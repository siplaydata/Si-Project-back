package com.example.cocktail.Home.UploadPost.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UtilService {
    List<String> sendToFlaskServer(List<MultipartFile> pictureData);
    List<MultipartFile> removeDuplicate(MultipartFile[] pictureData);
    List<String> removeDuplicate(String[] textData);
}
