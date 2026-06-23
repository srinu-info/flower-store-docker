package com.web.store.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.store.auth.dto.AuthResponse;
import com.web.store.auth.dto.ChangePasswordRequest;
import com.web.store.auth.dto.LoginRequest;
import com.web.store.auth.dto.RegisterRequest;
import com.web.store.auth.dto.UserResponse;
import com.web.store.auth.entity.User;
import com.web.store.auth.exception.InvalidCredentialsException;
import com.web.store.auth.exception.UserNotFoundException;
import com.web.store.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {
		return authService.register(request);
	}
	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) throws UserNotFoundException, InvalidCredentialsException {
		return authService.login(loginRequest);
	}
	@GetMapping("/user/{email}")
	public UserResponse getUser(@PathVariable("email") String email ) throws UserNotFoundException {
		return authService.getUser(email);
	}
	@PutMapping("/user/changePassword")
	public String changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws UserNotFoundException, InvalidCredentialsException {
		return authService.changePassword(changePasswordRequest);
	}
}
