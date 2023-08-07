package com.example.demo.APIComponent.Flask;

import com.example.demo.Home.upload.model.TextPictureData;
//import com.example.demo.Home.model.TextPictureDataDAO;
//import com.example.demo.Home.model.TextPictureDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FlaskApi {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String sendToFlaskServer(TextPictureData textPictureData) {
        try {
            ClientResponse clientResponse = (ClientResponse) webClientBuilder.build().post()
                    .uri("/upload")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(textPictureData)
                    .retrieve()
                    .toBodilessEntity()
                    .block();

            if (clientResponse.statusCode().is2xxSuccessful()) {
                return "Flask 서버에 전송 성공";
            } else {
                return "Flask 서버에 전송 실패: " + clientResponse.rawStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "서버 오류 발생";
        }
    }
}

//    public void sendToFlaskServer(TextPictureDataDTO textPictureDataDTO) {
//        try {
//            webClientBuilder.build().post()
//                    .uri("/upload")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(textPictureDataDTO)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .block();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void sendToFlaskServer(TextPictureDataDAO textPictureDataDAO) {
//        try {
//            webClientBuilder.build().post()
//                    .uri("/upload")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(textPictureDataDAO)
//                    .retrieve()
//                    .toBodilessEntity()
//                    .block();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

