package com.example.demo.log.Join.service;

import com.example.demo.log.Join.model.User;
import com.example.demo.log.Join.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;

    @Autowired
    public JoinService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User joinUser(User user) {
        // 회원가입 로직 구현
        // 비밀번호 암호화 등 필요한 처리를 수행할 수 있음
        // 예시로 그대로 저장만 하도록 구현
        return userRepository.save(user);
    }

    public boolean isUsernameAvailable(String username) {
        // 아이디 중복 확인 로직을 여기에 구현
        // DB 조회 등을 통해 아이디 중복 여부를 확인하고 결과를 반환
        // 예시로서는 단순히 true를 반환하도록 구현
        return userRepository.findByUserid(username) == null;
    }
}
