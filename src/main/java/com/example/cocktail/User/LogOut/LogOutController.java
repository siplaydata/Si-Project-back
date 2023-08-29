package com.example.cocktail.User.LogOut;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogOutController {
    @PostMapping
    public String logout(){
        return "로그아웃";
    }
}
