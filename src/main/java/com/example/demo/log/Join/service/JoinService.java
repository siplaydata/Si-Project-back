package com.example.demo.log.Join.service;

import com.example.demo.log.Join.model.User;
import com.example.demo.log.Join.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;

    // 회원가입 처리
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // 아이디 중복 확인
    public boolean isIdUnique(String userid) {
        return userRepository.findByUserId(userid) == null;
    }
}
