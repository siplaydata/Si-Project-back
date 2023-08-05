package com.example.demo.Home.service;

import com.example.demo.Home.model.TextPictureDataDAO;
import com.example.demo.Home.model.TextPictureDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Service
public class UploadService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseEntity<String> handleUploadAndProcess(
            String text1,
            String text2,
            String text3,
            MultipartFile picture1,
            MultipartFile picture2,
            MultipartFile picture3) {

        try {
            TextPictureDataDAO textPictureDataDAO = createTextPictureData(text1, text2, text3, picture1, picture2, picture3);
            TextPictureDataDTO textPictureDataDTO = createTextPictureDataDTO(textPictureDataDAO);

            printTextPictureDataDTO(textPictureDataDTO);

            // Flask 서버로 데이터 전송
            sendToFlaskServer(textPictureDataDTO);

            return ResponseEntity.ok("스프링이 받았다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("스프링이 못 받았다.");
        }
    }

    private TextPictureDataDAO createTextPictureData(
            String text1,
            String text2,
            String text3,
            MultipartFile picture1,
            MultipartFile picture2,
            MultipartFile picture3 ) {

        TextPictureDataDAO textPictureDataDAO = new TextPictureDataDAO();

        textPictureDataDAO.setText1(text1);
        textPictureDataDAO.setText2(text2);
        textPictureDataDAO.setText3(text3);

        textPictureDataDAO.setPicture1(picture1);
        textPictureDataDAO.setPicture2(picture2);
        textPictureDataDAO.setPicture3(picture3);

        return textPictureDataDAO;
    }

    private TextPictureDataDTO createTextPictureDataDTO(TextPictureDataDAO textPictureDataDAO) throws IOException {
        TextPictureDataDTO textPictureDataDTO = new TextPictureDataDTO();

        MultipartFile picture1 = textPictureDataDAO.getPicture1();
        MultipartFile picture2 = textPictureDataDAO.getPicture2();
        MultipartFile picture3 = textPictureDataDAO.getPicture3();

        if (picture1 != null) {
            textPictureDataDTO.setPicture1(picture1.getBytes());
        }
        if (picture2 != null) {
            textPictureDataDTO.setPicture2(picture2.getBytes());
        }
        if (picture3 != null) {
            textPictureDataDTO.setPicture3(picture3.getBytes());
        }

        return textPictureDataDTO;
    }

    private void sendToFlaskServer(TextPictureDataDTO textPictureDataDTO) {
        try {
            webClientBuilder.build().post()
                    .uri("/upload")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(textPictureDataDTO)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printTextPictureDataDTO(TextPictureDataDTO textPictureData) {
        System.out.println("===== TextPictureData =====");
        System.out.println("Text 1: " + textPictureData.getText1());
        System.out.println("Text 2: " + textPictureData.getText2());
        System.out.println("Text 3: " + textPictureData.getText3());
        System.out.println("Picture 1: " + textPictureData.getPicture1());
        System.out.println("Picture 2: " + textPictureData.getPicture2());
        System.out.println("Picture 3: " + textPictureData.getPicture3());
    }
}
