package com.example.cocktail.User.Join.controller;

import com.example.cocktail.User.Join.dto.JoinDTO;
import com.example.cocktail.User.Join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
public class JoinController {
    @Autowired
    private JoinService joinService;
    private void errorManager(String value, String errorMessage) {
        if (value.isEmpty()) { throw new IllegalArgumentException(errorMessage); }
    }
    @PostMapping
    public Boolean signUp(@RequestBody JoinDTO joinDTO) {
        if (joinDTO.getUsername() == null || joinDTO.getUsername().isEmpty()) { errorManager("", "아이디를 입력해주세요."); }
        if (joinDTO.getPassword() == null || joinDTO.getPassword().isEmpty()) { errorManager("", "비밀번호를 입력하세요."); }
        if (joinDTO.getPassword() == null || joinDTO.getPassword().isEmpty()) { errorManager("", "닉네임을 입력해주세요."); }

        if (checkUserid(joinDTO.getUsername()) && checkNickname(joinDTO.getNickname())) {
            return joinService.signUp(joinDTO);
        } else {
            if (!joinService.isIdUnique(joinDTO.getUsername())) {
                errorManager("", "중복된 아이디입니다.");
            }
            if (!joinService.isNicknameUnique(joinDTO.getNickname())){
                errorManager("", "중복된 닉네임 입니다.");
            }
            throw new RuntimeException("알 수 없는 오류");
        }
    }
    @PostMapping("/check-userid")
    public boolean checkUserid(@RequestParam String username) {
        errorManager(username, "아이디를 입력해 주세요.");
        return joinService.isIdUnique(username);
    }
    @PostMapping("/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        errorManager(nickname, "닉네임을 입력해 주세요.");
        return joinService.isNicknameUnique(nickname);
    }
}