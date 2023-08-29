package com.example.cocktail.User.Info.controller;

import com.example.cocktail.User.Info.dto.InfoDTO;
import com.example.cocktail.User.Info.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;
    public void checkAuthentication(Authentication authentication) {        // 이거는 로그인 했는지 안 했는지 체크
        if(authentication == null ||
                !infoService.checkUser(authentication.getName())){
            throw new AccessDeniedException("회원만 이용 가능 합니다."); }
    }
    @PostMapping("{password}")
    public boolean checkUserPassword(Authentication authentication,
                                     @RequestParam String password){         // 처음에 비번 확인 하기
        checkAuthentication(authentication);

        return infoService.checkUserPassword(authentication.getName(), password);
    }
    @GetMapping("/user")                                                  // 유저 계정 정보 받아오는거
    public InfoDTO getUserInfo(Authentication authentication){
        checkAuthentication(authentication);

        return infoService.getUserInfo(authentication.getName());
    }
    @PutMapping("/update") // 일단 수정은 닉네임 빼고 다 / 닉넴 수정되면 버그 / 다른거 수정 안 되면 버그
    public boolean updateUserInfo(Authentication authentication, InfoDTO infoDTO) {
        checkAuthentication(authentication);

        return infoService.updateUserInfo(authentication.getName(), infoDTO);
    }
    @DeleteMapping("delete")
    public boolean deleteUserInfo(Authentication authentication, @RequestParam String password) {
        checkAuthentication(authentication);

        return infoService.deleteUserInfo(authentication.getName(), password);
    }
}
