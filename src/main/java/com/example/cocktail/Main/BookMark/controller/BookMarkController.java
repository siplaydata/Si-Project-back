package com.example.cocktail.Main.BookMark.controller;

import com.example.cocktail.Main.BookMark.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload/{id}")
public class BookMarkController {
    @Autowired
    private BookMarkService bookMarkService;
    public void checkAuthentication(Authentication authentication) {
        if(authentication == null ||
                !bookMarkService.checkUser(authentication.getName())){
            throw new AccessDeniedException("회원만 이용 가능 합니다."); }
    }

    @PostMapping
    public String bookMark(@PathVariable Long id, Authentication authentication){
        checkAuthentication(authentication);

        return bookMarkService.saveRecipe(id, authentication.getName());
    }
}
