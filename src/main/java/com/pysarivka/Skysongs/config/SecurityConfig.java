package com.pysarivka.Skysongs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pysarivka.Skysongs.security.CustomUserDetailService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/login*").anonymous().requestMatchers("/api/**").authenticated().requestMatchers("/**").permitAll())
				.formLogin(formlogin -> formlogin.loginPage("/login")
//						.defaultSuccessUrl("/api/home", true)
						.failureUrl("/login?error=true").permitAll())
				.logout((logout) -> logout.logoutSuccessUrl("/allsongs"));
		return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
