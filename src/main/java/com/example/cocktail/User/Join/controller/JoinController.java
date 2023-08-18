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
    private void emptyManager(String value, String errorMessage) {
        if (value.isEmpty()) { throw new IllegalArgumentException(errorMessage); }
    }
    private void checkDTO(JoinDTO joinDTO){
        if (joinDTO.getUsername().isEmpty()) { throw new IllegalArgumentException("아이디를 입력해주세요."); }
        if (joinDTO.getPassword().isEmpty()) { throw new IllegalArgumentException("비밀번호를 입력하세요."); }
        if (joinDTO.getNickname().isEmpty()) { throw new IllegalArgumentException("닉네임을 입력해주세요."); }
        if (!checkUserid(joinDTO.getUsername())) { throw new IllegalArgumentException("중복된 아이디입니다."); }
        if (!checkNickname(joinDTO.getNickname())) { throw new IllegalArgumentException("중복된 닉네임입니다."); }
        if (!joinDTO.getPassword().equals(joinDTO.getCheckPassword())) {
            throw new IllegalArgumentException("비밀번호 확인이 일치 하지 않습니다.");
        }
    }
    @PostMapping
    public boolean signUp(@RequestBody JoinDTO joinDTO) {
        checkDTO(joinDTO);
        return joinService.signUp(joinDTO);
    }
    @PostMapping("/check-userid")
    public boolean checkUserid(@RequestParam String username) {
        emptyManager(username, "아이디를 입력 해주세요.");
        return joinService.isIdUnique(username);
    }
    @PostMapping("/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        emptyManager(nickname, "닉네임을 입력 해주세요.");
        return joinService.isNicknameUnique(nickname);
    }
}