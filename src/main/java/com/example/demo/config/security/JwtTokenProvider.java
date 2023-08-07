package com.example.demo.config.security;

import com.example.demo.log.Join.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret; // JWT 비밀키 설정

    @Value("${jwt.expiration}")
    private long jwtExpiration; // JWT 만료 시간 설정

    // 사용자 인증 정보로부터 JWT 토큰을 생성하는 메서드
    public String generateToken(Authentication authentication, User user, List<String> allowedRoles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        List<GrantedAuthority> authorities = new ArrayList<>(authentication.getAuthorities());
        String username = authentication.getName();

        List<GrantedAuthority> filteredAuthorities = authorities.stream()
                .filter(auth -> allowedRoles.contains(auth.getAuthority()))
                .collect(Collectors.toList());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId()); // 사용자 ID 추가
        claims.put("email", user.getEmail()); // 사용자 이메일 추가

        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", filteredAuthorities)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // JWT 토큰으로부터 사용자 인증 정보를 추출하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (claims.get("authorities") != null) {
            List<String> roles = (List<String>) claims.get("authorities");
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        String username = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }

    // HTTP 요청에서 JWT 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // JWT 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // JWT 토큰 갱신 메서드
    public String refreshToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // JWT 토큰 무효화 메서드
    public void invalidateToken(String token) {
        // 무효화 로직 구현
        // 실제로는 데이터베이스나 캐시 등에 해당 토큰을 저장하거나 블랙리스트에 추가하는 등의 작업을 수행해야 합니다.
        // 이는 사용자 로그아웃이나 토큰 갱신 등의 시나리오에서 토큰을 무효화하는 방법입니다.
        // 아래는 간단한 예시입니다:

        // 1. 데이터베이스에 토큰을 저장하거나 블랙리스트에 추가
        // tokenBlacklistService.addToBlacklist(token);

        // 2. 저장한 토큰을 검증 시 블랙리스트에 있는지 확인
        // if (tokenBlacklistService.isBlacklisted(token)) {
        //     throw new TokenInvalidException("Token is invalid.");
        // }
    }

}
