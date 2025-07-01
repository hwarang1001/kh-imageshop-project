package com.kh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true, securedEnabled=true) 
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("security 환경설정 시작 ...");
		// csrf 토큰 비활성화
		http.csrf().disable();
		
		// 로그인 인가정책
		http.authorizeRequests().requestMatchers("/board/**").authenticated();
		http.authorizeRequests().requestMatchers("/manager/**").hasRole("MANAGER");
		http.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().permitAll();
		
		// 로그인 설정
		http.formLogin();
		
		return http.build();
	}

	// 암호화 처리기 빈 등록
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}