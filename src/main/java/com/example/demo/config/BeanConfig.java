package com.example.demo.config;


import com.example.demo.common.base.filter.AuthTokenFilter;
import com.example.demo.common.base.jwt.AuthEntryPointJwt;
import com.example.demo.common.security.common.JwtTokenCommon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    JwtTokenCommon jwtTokenCommon() {
        return new JwtTokenCommon();
    }

    @Bean
    AuthEntryPointJwt authEntryPointJwt() {
        return new AuthEntryPointJwt();
    }
}
