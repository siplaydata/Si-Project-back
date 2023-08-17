package com.example.cocktail.Home.UploadPost.service;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilServiceImpl implements UtilService {
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
    public List<MultipartFile> removeDuplicate(MultipartFile[] pictureData) {
        return List.of(pictureData).stream()
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<String> removeDuplicate(String[] textData) {
        return Arrays.stream(textData)
                .distinct()
                .collect(Collectors.toList());
    }
}
