package com.example.demo.User.LogIn.service;

import com.example.demo.User.LogIn.model.LoginUser;
import com.example.demo.User.LogIn.repository.LoginRepository;
import com.example.demo.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//
//import com.example.demo.Component.component.JwtUtils;
//import com.example.demo.User.Join.model.JoinUser;
//import com.example.demo.User.LogIn.DTO.JwtResponseDTO;
//import com.example.demo.User.LogIn.DTO.LoginDTO;
//import com.example.demo.User.LogIn.repository.LoginRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class LoginService {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    @Autowired
//    private LoginRepository loginRepository;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//    public JwtResponseDTO login(LoginDTO loginRequest) {
//        JoinUser joinUser = loginRepository.findByUserid(loginRequest.getUserid());
//
//        if (joinUser != null && encoder.matches(loginRequest.getPassword(), joinUser.getPassword())) {
//            try {
//                // 인증 매니저를 통해 로그인 시도
//                Authentication authentication = authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(loginRequest.getUserid(), loginRequest.getPassword()));
//
//                // 인증 객체를 SecurityContextHolder에 설정
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                // UserDetails를 가져와서 토큰 생성
//                UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserid());
//                String token = jwtUtils.generateToken(userDetails.getUsername());
//                Date expirationDate = jwtUtils.extractExpiration(token); // 토큰의 만료 시간 추출
//
//                return new JwtResponseDTO(token, "Bearer", expirationDate.getTime() / 1000);
//            } catch (AuthenticationException e) {
//                // 로그인 실패 처리
//                System.out.println("로그인 실패 예외: " + e.getMessage());
//                e.printStackTrace(); // 예외 스택 트레이스 출력
//                return null;
//            }
//        }
//        // 로그인 실패 처리
//        return null;
//    }
//}
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

        Long expireTimeMs = 1000 * 60 * 60l;
        // 앞에서 에러 안나면 토큰 발행.
        return JwtUtil.createJwt(loginUser.getNickname(), key, expireTimeMs);
    }
}
