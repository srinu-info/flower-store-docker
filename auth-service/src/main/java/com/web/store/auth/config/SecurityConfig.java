package com.web.store.auth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.web.store.auth.security.JwtAuthFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthFilter authFilter;
@Bean
public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		http.csrf(c->c.disable())
		.authorizeHttpRequests(auth->auth
	            .requestMatchers("/auth/**", "/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**","/swagger-ui.html","/auth/login").permitAll()				
	            .requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/user/**").hasRole("CUSTOMER")
				.anyRequest().authenticated()
				)
		.headers(headers -> headers.frameOptions(frame -> frame.disable()))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.addFilterAfter(authFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors(Customizer.withDefaults());
		return http.build();
		
	}
//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//
//	    CorsConfiguration configuration =
//	            new CorsConfiguration();
//
//	    configuration.setAllowedOrigins(
//	            List.of("http://localhost:4200"));
//
//	    configuration.setAllowedMethods(
//	            List.of("GET","POST","PUT","DELETE","OPTIONS"));
//
//	    configuration.setAllowedHeaders(
//	            List.of("*"));
//
//	    configuration.setAllowCredentials(true);
//
//	    UrlBasedCorsConfigurationSource source =
//	            new UrlBasedCorsConfigurationSource();
//
//	    source.registerCorsConfiguration(
//	            "/**",
//	            configuration);
//
//	    return source;
//	}

}
