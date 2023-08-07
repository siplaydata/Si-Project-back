package com.example.demo.menu.find.controller;

import com.example.demo.menu.find.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/find")
    public String getAllInfoAsJson() {
        // Info 테이블의 모든 정보를 JSON 형태로 반환
        return menuService.getAllInfoAsJson();
    }
}
