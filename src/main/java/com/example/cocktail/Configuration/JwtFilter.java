package com.example.cocktail.Configuration;

import com.example.cocktail.User.LogIn.service.LoginService;
import com.example.cocktail.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final LoginService loginService;
    private final String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("qwerty ")) {
            log.error("권한이 없습니다.");
            filterChain.doFilter(request, response);
            return;
        }
        // Token 꺼내기
        String token = authorization.split(" ")[1];

        // Token Expired 되었는지 여부
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("다시 로그인 해주세요.");
            filterChain.doFilter(request, response);
            return;
        }

        // UserName Token에서 꺼내기
        String userNickName = JwtUtil.getUserNickName(token, secretKey);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userNickName, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail을 넣어줍니다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}