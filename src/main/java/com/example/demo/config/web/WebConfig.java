package com.example.demo.config.web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // HTTP 메시지 컨버터를 확장하는 메서드
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 모든 HTTP 메시지 컨버터를 순회하며 확인
        for (HttpMessageConverter<?> converter : converters) {
            // 현재 컨버터가 MappingJackson2HttpMessageConverter인지 확인
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                // MappingJackson2HttpMessageConverter로 캐스팅하여 ObjectMapper 설정
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                // ObjectMapper를 가져와서 빈 컬렉션을 직렬화 시도 시 실패하지 않도록 설정
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            }
        }
    }

    // 파일 업로드 최대 크기 설정을 위한 빈 생성 메서드
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        // MultipartConfigFactory를 사용하여 파일 업로드 관련 설정 생성
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(100)); // 최대 파일 크기 설정
        factory.setMaxRequestSize(DataSize.ofMegabytes(100)); // 최대 요청 크기 설정
        return factory.createMultipartConfig();
    }

    // WebClient를 위한 빈 생성 메서드
    @Bean
    public WebClient.Builder webClientBuilder(@Value("${flask}") String flaskServerUrl) {
        // WebClient.Builder를 생성하고 baseUrl을 flaskServerUrl로 설정하여 반환
        return WebClient.builder().baseUrl(flaskServerUrl);
    }
}


