package com.example.cocktail.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
                objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            }
        }
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    // 파일 업로드 최대 크기 설정
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(100)); // 최대 파일 크기 설정
        factory.setMaxRequestSize(DataSize.ofMegabytes(100)); // 최대 요청 크기 설정
        return factory.createMultipartConfig();
    }
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

}
