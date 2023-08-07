package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {
    /**
     * JwtTokenFilter 생성자.
     *
     * @param jwtTokenProvider JwtTokenProvider 인스턴스
     */
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 요청 필터링 및 JWT 토큰 처리를 수행합니다.
     *
     * @param request  HTTP 요청
     * @param response HTTP 응답
     * @param chain    다음 필터 체인
     * @throws IOException      입출력 예외
     * @throws ServletException 서블릿 예외
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            // HTTP 요청으로부터 JWT 토큰 추출
            String token = jwtTokenProvider.resolveToken(request);

            // 추출한 토큰이 유효하고 인증이 가능한 경우
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰을 사용하여 인증 정보 생성
                Authentication auth = jwtTokenProvider.getAuthentication(token);

                // Spring Security의 SecurityContextHolder에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(auth); // 인증 설정
            }
        } catch (Exception e) {
            // 예외 처리: 토큰이 유효하지 않은 경우 등의 예외를 처리해야 함
            // 이 예제에서는 간단하게 로깅 후 무시
            System.out.println("Token validation failed: " + e.getMessage());
        }
        // 다음 필터로 요청과 응답 전달
        chain.doFilter(request, response); // 다음 필터로 넘기기
    }

}
