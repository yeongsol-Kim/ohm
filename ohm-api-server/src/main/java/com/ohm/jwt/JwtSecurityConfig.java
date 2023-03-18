package com.ohm.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;


//생성한 TokenProvider,JwtFilter를 SecurityConfig에 적용할때 사용함
@Component
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    private TokenProvider tokenProvider;
//
//    public JwtSecurityConfig(TokenProvider tokenProvider){
//        this.tokenProvider = tokenProvider;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        JwtFilter customFilter = new JwtFilter(tokenProvider);
//        //Secirity로직에 필터를 등록한다.
//        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//    }

    private final JwtFilter jwtFilter;

    // JwtFilter를 Security로직에 필터를 등록
    @Override
    public void configure(HttpSecurity http) {
        // Security 로직에 필터를 등록
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
