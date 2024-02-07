package com.team.ourblog.config;

import com.team.ourblog.jwt.JwtAccessDeniedHandler;
import com.team.ourblog.jwt.JwtAuthenticationEntryPoint;
import com.team.ourblog.jwt.JwtFilter;
import com.team.ourblog.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Configuration
@Component
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //폼 기반 로그인 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                //HTTP 기본 인증 비활성화
                .httpBasic(AbstractHttpConfigurer::disable)
                //CSRF 공격 방어 기능 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                //세션 관리 정책 설정
                //세션 인증을 사용하지 않고, jWT 를 사용하여 인증하기 때문에, 세션 불필요
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/member/join","/member/login","member/checkEmail","member/checkNickname").permitAll()
                                .requestMatchers("/posting/list","posting/detail/**").permitAll()
                                .requestMatchers("comment/list").permitAll()
                                .requestMatchers("/heart/anonymous/**").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                // exception handling 할 때 만든 클래스를 추가 
                .exceptionHandling(excep -> excep.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
                .with(new JwtSecurityConfig(tokenProvider), Customizer.withDefaults());

        return http.build();
    }
}



