package com.web.store.auth.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.store.auth.dto.AuthResponse;
import com.web.store.auth.dto.ChangePasswordRequest;
import com.web.store.auth.dto.LoginRequest;
import com.web.store.auth.dto.RegisterRequest;
import com.web.store.auth.dto.UserResponse;
import com.web.store.auth.entity.Role;
import com.web.store.auth.entity.User;
import com.web.store.auth.exception.InvalidCredentialsException;
import com.web.store.auth.exception.UserNotFoundException;
import com.web.store.auth.repository.AuthRepository;
import com.web.store.auth.security.JwtUtil;
@Service
public class AuthService {
private static final Logger logger =LoggerFactory.getLogger(AuthService.class);
@Autowired
AuthRepository authRepository;
@Autowired
BCryptPasswordEncoder passwordEncoder;
@Autowired
JwtUtil jwtUtil;

public String register(RegisterRequest request) {
	logger.info("Checking the email alredy present in DB or not");
	if(authRepository.findByEmail(request.getEmail()).isPresent()) {
		logger.info("Email Alreaady Exist..");
		return "Email Already Exist!";
	}
	logger.info("The Present given email is unique");
	User user=new User();
	user.setFirstName(request.getFirstName());
	user.setLastName(request.getLastName());
	user.setEmail(request.getEmail());
	user.setPassword(passwordEncoder.encode(request.getPassword()));;
	user.setTitle(request.getTitle());
	user.setCity(request.getCity());
	user.setCountry(request.getCountry());
	user.setPhone(request.getPhone());
	user.setRole(Role.CUSTOMER);
	authRepository.save(user);		
	logger.info("User Registered Successfully");
	return "User Registered Successfully!";
}
public AuthResponse login(LoginRequest loginRequest) throws UserNotFoundException, InvalidCredentialsException {
	User user= authRepository.findByEmail(loginRequest.getEmail())
			.orElseThrow(()->{ 
				logger.error("User Not Found :{}" , loginRequest.getEmail());
		return new UserNotFoundException("User not found");
		});
	
	if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
		logger.warn("Invalid Paassword :{}"+loginRequest.getEmail());
		throw new InvalidCredentialsException("Invalid password");
	}
	String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    return new AuthResponse(token, user.getRole().name());
}

public String changePassword(ChangePasswordRequest passwordRequest) throws UserNotFoundException, InvalidCredentialsException {
	User user= authRepository.findByEmail(passwordRequest.getEmail())
			.orElseThrow(()->{ 
				logger.error("User Not Found :{}" , passwordRequest.getEmail());
		return new UserNotFoundException("User not found");
		});
	
	if(!passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())){
		throw new InvalidCredentialsException("Old and New Password are not matching.");
	}
	user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
	authRepository.save(user);
	return "Password Changed Successfully!";
	
}

public UserResponse getUser(String email) throws UserNotFoundException {
	User user=authRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException("Email Not Valid"));
	UserResponse response= new UserResponse();
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setEmail(user.getEmail());
    response.setCity(user.getCity());
    response.setCountry(user.getCountry());
    response.setPhone(user.getPhone());
	
	
	return response;
}
}
