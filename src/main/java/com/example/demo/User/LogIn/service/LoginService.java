package com.example.demo.User.LogIn.service;

import com.example.demo.User.LogIn.model.LoginUser;
import com.example.demo.User.LogIn.repository.LoginRepository;
import com.example.demo.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Value("${jwt.token.secret}")
    private String key;

    public LoginUser findByUserName(String username) {
        return loginRepository.findByUsername(username);
    }
    public String login(String username, String password) throws Exception {
        // userid 없음
        LoginUser loginUser = findByUserName(username);

        Long expireTimeMs = 1000 * 60 * 60 * 24L; // 24시간 (1일)
        // 앞에서 에러 안나면 토큰 발행.
        return JwtUtil.createJwt(loginUser.getNickname(), key, expireTimeMs);
    }
}
