package com.project.searchone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.searchone.component.JwtAuthenticationEntryPoint;
import com.project.searchone.component.TokenProvider;
import com.project.searchone.service.UserLoginService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserLoginService userLoginService;
	private final TokenProvider tokenProvider;

	public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth
		.userDetailsService(userLoginService)
		.passwordEncoder(passwordEncoder());
        return auth.build();
    }

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
			
			
				
				//.requestMatchers("/").hasRole("USER")
				.requestMatchers("/api/authenticate").permitAll()
				.anyRequest().authenticated()

				
				
			)
			.formLogin((form) -> form
				.permitAll()
			)
			.logout((logout) -> logout.permitAll())
			
			.apply(new JwtSecurityConfig(tokenProvider));
			
			
			;

		return http.build();
	}

	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userLoginService);

        return daoAuthenticationProvider;
    }

	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}