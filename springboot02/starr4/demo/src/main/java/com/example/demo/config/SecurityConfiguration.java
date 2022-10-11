package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/")
				//.usernameParameter("email") 사용자 입력시 로그인할 이름으로 사용될 form태그 name
				.failureUrl("/login/error")
				.loginProcessingUrl("/loginProc")//로그인처리페이지
				.and()
				.logout()
				.logoutSuccessUrl("/")
		;
		//https://deftkang.tistory.com/217
		http.authorizeRequests()
				.mvcMatchers("/h2-console","/h2-console/**").permitAll()

				.mvcMatchers("/user/**").authenticated()//권한을 가진사람 접근가능
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.mvcMatchers("/**","/","/joinProc").permitAll()
				.anyRequest().permitAll()
				.and()
				.csrf()
				.ignoringAntMatchers("/**")    // csrf를 사용하지 않을 예정이여서 모든 페이지에서 무시하려고 추가.
				.ignoringAntMatchers("/h2-console/**")    // 여기!
				.and()
				.headers().frameOptions().disable()   //
		;
		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
