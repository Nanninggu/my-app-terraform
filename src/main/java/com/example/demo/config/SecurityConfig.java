package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    // Security Filter Chain 빈을 정의하여 http 보안 설정을 구성합니다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 요청 권한을 설정합니다.
                .authorizeHttpRequests(authz -> authz
                        // H2 콘솔에 대한 액세스를 허용합니다.
                        .requestMatchers("/h2-console/**").permitAll()
                        // "/users/**" 경로에 대한 요청은 인증이 필요하지 않습니다.
                        .requestMatchers("/users/**").permitAll()
                        // /server-environment 경로에 대한 요청은 인증이 필요하지 않습니다.
                        .requestMatchers("/server-environment").permitAll()
                        // "/view/users" 경로에 대한 요청은 인증이 필요합니다.
                        .requestMatchers("/view/users").authenticated()
                        // 그 외의 모든 요청은 인증이 필요합니다.
                        .anyRequest().authenticated()
                )
                // 폼 로그인을 설정합니다.
                .formLogin(form -> form
                        // 로그인 성공 시 "/view/users"로 리다이렉트합니다.
                        .defaultSuccessUrl("/view/users", true)
                )
                // HTTP 기본 인증을 설정합니다.
                .httpBasic(withDefaults());

        return http.build();
    }

    // UserDetailsService 빈을 정의하여 인메모리 사용자 세부 정보를 설정합니다.
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.builder()
                // 사용자 이름을 "user"로 설정합니다.
                .username("user")
                // 비밀번호를 "password"로 설정하고 암호화합니다.
                .password(passwordEncoder().encode("password"))
                // 사용자 역할을 "USER"로 설정합니다.
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // PasswordEncoder 빈을 정의하여 비밀번호를 암호화합니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
