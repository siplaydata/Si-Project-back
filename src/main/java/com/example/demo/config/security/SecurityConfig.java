package com.example.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // JwtTokenProvider 주입

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // 실제로는 사용자 정보를 데이터베이스 등에서 조회해오는 코드를 작성해야 합니다.
                // 여기서는 간단히 하드코딩된 사용자 정보를 반환합니다.
                if ("user".equals(username)) {
                    return User.builder()
                            .username("user")
                            .password(passwordEncoder().encode("password"))
                            .roles("USER")
                            .build();
                } else {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:5000", "http://127.0.0.1:3000")); // 허용할 도메인 목록
        configuration.setAllowedMethods(Arrays.asList("GET", "POST")); // 허용할 HTTP 메서드 목록
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and() // CORS 설정
                .csrf().disable() // CSRF 공격 방어 기능 비활성화

                .authorizeRequests() // 권한 설정 시작
                .antMatchers("/**").permitAll() // 공개 API
                .antMatchers("/community/**").authenticated() // 인증된 사용자만 접근 가능한 API
                .and() // 권한 설정 종료

                .httpBasic() // Basic Authentication 활성화
                .and() // Basic Authentication 설정 종료

                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) // JWT 필터 추가

                .formLogin() // 폼 기반 로그인 설정 시작
                .permitAll() // 로그인 페이지는 인증 없이 접근 허용
                .and() // 폼 기반 로그인 설정 종료

                .logout() // 로그아웃 설정 시작
                .logoutUrl("/home") // 로그아웃 URL 설정
                .permitAll(); // 로그아웃 URL은 인증 없이 접근 허용
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 정적 리소스 (CSS, JS 등)는 인증 없이 접근 가능하도록 설정
        web.ignoring().antMatchers("/static/**");
    }
}