package com.project.searchone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.project.searchone.component.TokenProvider;
import com.project.searchone.service.UserLoginService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserLoginService userLoginService;
	private final TokenProvider tokenProvider;

	// @Bean
    // public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
    //     EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
    //         EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
    //     contextSourceFactoryBean.setPort(0);
    //     return contextSourceFactoryBean;
    // }

	//@Bean
	// public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    //     auth
	// 	.userDetailsService(userLoginService)
	// 	.passwordEncoder(passwordEncoder());
    //     return auth.build();
    // }

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/api/authorize").permitAll()
			//.requestMatchers("/user/**").hasRole("USER")
				//.requestMatchers("/**").permitAll()
				//.requestMatchers("/**").hasRole("USER")
				.anyRequest().authenticated()
				
			)
			// .formLogin((form) -> form
			// 	.permitAll()
			// )
			.logout((logout) -> logout.permitAll())
			.apply(new JwtSecurityConfig(tokenProvider))
			
			;

		return http.build();
	}

	// @Bean
    // DaoAuthenticationProvider authenticationProvider(){
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    //     daoAuthenticationProvider.setUserDetailsService(this.userLoginService);

    //     return daoAuthenticationProvider;
    // }

	@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user =
	// 		 User.withDefaultPasswordEncoder()
	// 			.username("user")
	// 			.password("password")
	// 			.roles("USER")
	// 			.build();

	// 	return new InMemoryUserDetailsManager(user);
	// }
}