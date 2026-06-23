package com.web.store.review.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.store.review.security.JwtAuthFilter;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	 @Autowired
	    private JwtAuthFilter jwtAuthFilter;

	    @Bean
	    public SecurityFilterChain securityFilterChain(
	            HttpSecurity http) throws Exception {

	        http
	                .csrf(csrf -> csrf.disable())

	                .authorizeHttpRequests(auth -> auth

	                        // Swagger
	                        .requestMatchers(
	                                "/swagger-ui/**",
	                                "/swagger-ui.html",
	                                "/v3/api-docs/**"
	                        ).permitAll()

	                        // H2 Console
	                        .requestMatchers(
	                                "/h2-console/**"
	                        ).permitAll()

	                        // View reviews - Public
	                        .requestMatchers(
	                                HttpMethod.GET,
	                                "/feedback"
	                        ).permitAll()

	                        // Submit review - CUSTOMER only
	                        .requestMatchers(
	                                HttpMethod.POST,
	                                "/feedback"
	                        ).hasRole("CUSTOMER")

	                        .anyRequest()
	                        .authenticated()
	                )

	                .headers(headers ->
	                        headers.frameOptions(
	                                frame -> frame.disable()
	                        )
	                )

	                .sessionManagement(session ->
	                        session.sessionCreationPolicy(
	                                SessionCreationPolicy.STATELESS
	                        )
	                )

	                .addFilterBefore(
	                        jwtAuthFilter,
	                        UsernamePasswordAuthenticationFilter.class
	                );

	        return http.build();
	    }
	}