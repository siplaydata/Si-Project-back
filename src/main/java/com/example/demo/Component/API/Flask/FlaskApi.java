package com.example.demo.Component.API.Flask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import org.springframework.web.multipart.MultipartFile;

@Component
public class FlaskApi {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String sendToFlaskServer(MultipartFile[] pictureData) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            for (MultipartFile file : pictureData) {
                if (file != null) {
                    ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                        @Override
                        public String getFilename() {
                            return file.getOriginalFilename();
                        }
                    };
                    body.add("images", resource);
                }
            }

            WebClient.RequestHeadersSpec<?> request = webClientBuilder.build().post()
                    .uri("/upload")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body));

            ClientResponse clientResponse = request.exchange().block();

            if (clientResponse != null && clientResponse.statusCode().is2xxSuccessful()) {
                Mono<String> result = clientResponse.bodyToMono(String.class);
                String flaskResult = result.block();
                return flaskResult;
            } else {
                return "Flask 서버 전송 실패 : " + (clientResponse != null ? clientResponse.rawStatusCode() : "Unknown");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "서버 오류 발생 : 스프링에서 출력 내용 확인";
        }
    }
}
// 위의 코드는 String으로 반환

//@Component
//public class FlaskApi {
//    @Autowired
//    private WebClient.Builder webClientBuilder;
//
//    public int[] sendToFlaskServer(MultipartFile[] pictureData) {
//        try {
//            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//
//            for (int i = 0; i < pictureData.length; i++) {
//                MultipartFile file = pictureData[i];
//                if (file != null) {
//                    ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
//                        @Override
//                        public String getFilename() {
//                            return file.getOriginalFilename();
//                        }
//                    };
//                    body.add("images", resource);
//                }
//            }
//
//            WebClient.RequestHeadersSpec<?> request = webClientBuilder.build().post()
//                    .uri("/upload")
//                    .contentType(MediaType.MULTIPART_FORM_DATA)
//                    .body(BodyInserters.fromMultipartData(body));
//
//            ClientResponse clientResponse = request.exchange().block();
//
//            if (clientResponse != null && clientResponse.statusCode().is2xxSuccessful()) {
//                Mono<int[]> result = clientResponse.bodyToMono(int[].class);
//                int[] flaskResult = result.block();
//                return flaskResult;
//            } else {
//                return new int[0]; // 실패 시 빈 배열 또는 적절한 값 반환
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new int[0]; // 에러 발생 시 빈 배열 또는 적절한 값 반환
//        }
//    }
//}


