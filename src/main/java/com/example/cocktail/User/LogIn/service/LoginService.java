package com.example.cocktail.User.LogIn.service;

import com.example.cocktail.User.LogIn.model.LoginUser;
import com.example.cocktail.User.LogIn.repository.LoginRepository;
import com.example.cocktail.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private boolean logInManager(LoginUser loginUser, String password){
        if (loginUser == null || loginUser.getUsername() == null) {
            throw new UsernameNotFoundException("일치 하는 아이디가 없습니다.");
        }
        if (!encoder.matches(loginUser.getPassword(), password)) {
            throw new BadCredentialsException("비밀번호가 일치 하지 않습니다.");
        }
        return true;
    }
    public String logIn(String username, String password){
        LoginUser loginUser = loginRepository.findByUsername(username);

        if (logInManager(loginUser, password)){
            // 24시간 (1일)
            Long expireTimeMs = 1000 * 60 * 60 * 24L;
            return JwtUtil.createJwt(loginUser.getNickname(), key, expireTimeMs);
        } else {
            return null;
        }
    }
}

