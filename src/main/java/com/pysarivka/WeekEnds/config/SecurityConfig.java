package com.pysarivka.WeekEnds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pysarivka.WeekEnds.security.CustomUserDetailService;
import com.pysarivka.WeekEnds.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(AbstractHttpConfigurer::disable)
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        
	        // ДОДАЄМО ЦЕЙ БЛОК: Примусово повертаємо 401 для REST-запитів без токена
	        .exceptionHandling(exceptions -> exceptions
	            .authenticationEntryPoint((request, response, authException) -> {
	                response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
	                response.setContentType("application/json");
	                response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");
	            })
	        )
	        
	        .authorizeHttpRequests(auth -> auth
	        	    // Захищаємо запити отримання та оновлення профілю
	        		.requestMatchers("/auth/me", "/auth/update").authenticated() 
	        	    .requestMatchers("/auth/login", "/auth/register").permitAll() 
	        	    
	        	    // ДОДАЙТЕ ЦЕЙ РЯДОК: Дозволить бачити реальні помилки 500/400 замість фейкової 401
	        	    .requestMatchers("/error").permitAll() 
	        	    
	        	    .requestMatchers("/days/**", "/locations/**", "/operations/**").authenticated()
	        	    .anyRequest().permitAll()
	        	)
	        .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	// Цей біни потрібен в AuthController, щоб перевіряти логін/пароль вручну
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
