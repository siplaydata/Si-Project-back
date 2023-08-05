package com.example.demo.menu.find.service;

import com.example.demo.menu.find.model.Info;
import com.example.demo.menu.find.repository.MenuRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    @Autowired
    private  ObjectMapper objectMapper;
    @Autowired
    private MenuRepository menuRepository;

    public String getAllInfoAsJson() {
        // Info 테이블의 모든 레코드를 조회하여 JSON 형태로 변환하여 반환
        List<Info> infoList = menuRepository.findAll();
        return convertInfoListToJson(infoList);
    }

    private String convertInfoListToJson(List<Info> infoList) {
        try {
            return objectMapper.writeValueAsString(infoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]"; // 예외 발생 시 빈 배열 반환
        }
    }

    private String convertInfoToJson(Info info) {
        // Info 객체를 JSON 형태의 문자열로 변환
        return "{\"name\": \"" + info.getName() + "\", \"picture\": \"" + info.getPicture() + "\"}";
    }
}