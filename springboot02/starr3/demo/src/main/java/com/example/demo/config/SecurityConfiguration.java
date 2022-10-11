package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()//csrf를 사용하지 않음
			.authorizeRequests()//권한설정시작
			.antMatchers("/user/**").authenticated()//권한을 가진사람 접근가능
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
			//.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")//ADMIN사용자만 권한접근가능
			.anyRequest().permitAll()//나머지요청은 모든사용자.
			.and()//작업종료
			.formLogin()//로그인 관련설정
			.loginPage("/login")//login페이지
			.loginProcessingUrl("/loginProc")//로그인처리페이지
			.defaultSuccessUrl("/")//성공시페이지
			.and()//작업종료
			.logout()//로그아웃관련 설정
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login");//작업종료
		return http.build();
	}
}
