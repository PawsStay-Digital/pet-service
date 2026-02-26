package com.pawsstay.petservice.config;

import com.pawsstay.petservice.filter.InternalRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final InternalRequestFilter internalRequestFilter;

    public SecurityConfig(InternalRequestFilter internalRequestFilter) {
        this.internalRequestFilter = internalRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 關閉 CSRF（因為是無狀態的 REST API，透過 JWT 驗證）
                .csrf(AbstractHttpConfigurer::disable)
                // 在 Spring Security 的認證 Filter 之前，先執行我們的內部 Header 檢查
                .addFilterBefore(internalRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        // 所有請求都必須通過 InternalRequestFilter 的檢查
                        // 這裡 permitAll() 只代表「Spring Security 本身不做額外攔截」
                        // 真正的驗證邏輯已在 InternalRequestFilter 中完成
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
