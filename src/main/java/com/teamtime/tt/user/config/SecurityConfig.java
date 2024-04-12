package com.teamtime.tt.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/admin/**").hasRole("ADMIN")
//				.requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/", "/user/**").permitAll()
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
		);

		http.formLogin((auth) -> auth
				.loginPage("/user/login.do")
				.loginProcessingUrl("/user/login.do")
				.defaultSuccessUrl("/")
				.usernameParameter("userId")
				.passwordParameter("userPw")
				
				.failureUrl("/error")
				.permitAll()
		);
		
		http.logout((auth) -> auth
					.deleteCookies("remove")
					.invalidateHttpSession(false)
					.logoutUrl("/user/logout.do")
					.logoutSuccessUrl("/")
		);

		http.csrf((auth) -> auth
				.disable()
		);

		return http.build();
	}
	
}
