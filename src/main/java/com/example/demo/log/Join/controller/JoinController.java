package com.example.demo.log.Join.controller;

import com.example.demo.log.Join.model.User;
import com.example.demo.log.Join.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
public class JoinController {
    @Autowired
    private JoinService joinService;

    @PostMapping
    public ResponseEntity<User> join(@RequestBody User user) {
        // 회원가입 요청 처리
        User newUser = joinService.joinUser(user);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        boolean isAvailable = joinService.isUsernameAvailable(username);
        if (isAvailable) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
