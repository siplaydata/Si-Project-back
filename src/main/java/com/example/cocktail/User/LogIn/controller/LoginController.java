package com.example.cocktail.User.LogIn.controller;

import com.example.cocktail.User.LogIn.dto.LoginDTO;
import com.example.cocktail.User.LogIn.model.LoginUser;
import com.example.cocktail.User.LogIn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private void dtoIsNotEmpty(LoginDTO dto){
        if (dto.getUsername().isEmpty()) { throw new IllegalArgumentException("아이디를 입력하세요."); }
        if (dto.getPassword().isEmpty()) { throw new IllegalArgumentException("비밀번호를 입력하세요."); }
    }
    @PostMapping
    public String logIn(@RequestBody LoginDTO dto){
        dtoIsNotEmpty(dto);
        return loginService.logIn(dto.getUsername(), dto.getPassword());
    }
}
