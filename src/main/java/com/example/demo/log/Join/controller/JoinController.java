package com.example.demo.log.Join.controller;

import com.example.demo.log.Join.model.User;
import com.example.demo.log.Join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
public class JoinController {
    @Autowired
    private JoinService joinService;

    // 회원가입 요청 처리
    @PostMapping("/member")
    private User joinMember(@RequestBody User user) {
        // 회원 정보 저장
        return joinService.registerUser(user);
    }

    @PostMapping("/check-userid")
    private boolean checkUserId(@RequestParam String userid) {
        return joinService.isIdUnique(userid);
    }
}
