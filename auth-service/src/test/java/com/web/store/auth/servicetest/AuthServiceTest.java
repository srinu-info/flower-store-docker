package com.web.store.auth.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.web.store.auth.dto.AuthResponse;
import com.web.store.auth.dto.ChangePasswordRequest;
import com.web.store.auth.dto.LoginRequest;
import com.web.store.auth.dto.RegisterRequest;
import com.web.store.auth.entity.Role;
import com.web.store.auth.entity.User;
import com.web.store.auth.exception.InvalidCredentialsException;
import com.web.store.auth.exception.UserNotFoundException;
import com.web.store.auth.repository.AuthRepository;
import com.web.store.auth.security.JwtUtil;
import com.web.store.auth.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

	@Mock
	AuthRepository authRepository;
	@Mock
	BCryptPasswordEncoder passwordEncoder;
	@Mock
	JwtUtil jwtUtil;
	@InjectMocks
	AuthService authService;

	@Test
	public void registerTest() {
		RegisterRequest register = new RegisterRequest();
		register.setTitle("MR");
		register.setFirstName("Srinu");
		register.setLastName("Y");
		register.setEmail("baby@gmail.com");
		register.setPassword("sri123");
		register.setCity("Hyd");
		register.setCountry("India");
		register.setPhone(9845671233L);
		register.setRole(Role.CUSTOMER);

		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.empty());
		when(passwordEncoder.encode("sri123")).thenReturn("encodedPassword");

		String result = authService.register(register);

		assertEquals("User Registered Successfully!", result);

	}

	@Test
	public void registerTest_Filed() {
		RegisterRequest register = new RegisterRequest();
		register.setEmail("baby@gmail.com");

		User existing = new User();
		existing.setEmail("baby@gmail.com");

		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.of(existing));

		String result = authService.register(register);

		assertEquals("Email Already Exist!", result);

	}

	@Test
	public void loginTest() throws UserNotFoundException, InvalidCredentialsException {
		User user = new User();
		user.setEmail("baby@gmail.com");
		user.setPassword("encodedpassword");
		user.setRole(Role.CUSTOMER);
		LoginRequest request = new LoginRequest();
		request.setEmail("baby@gmail.com");
		request.setPassword("plainpassword");

		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("plainpassword", "encodedpassword")).thenReturn(true);
		when(jwtUtil.generateToken("baby@gmail.com", "CUSTOMER")).thenReturn("fakeToken");

		AuthResponse authResponse = authService.login(request);

		assertEquals("fakeToken", authResponse.getToken());
		assertEquals("CUSTOMER", authResponse.getRole());

	}

	@Test
	public void loginTest_Filed() throws UserNotFoundException, InvalidCredentialsException {
		User user = new User();
		user.setEmail("baby@gmail.com");
		user.setPassword("encodedpassword");
		user.setRole(Role.CUSTOMER);
		LoginRequest request = new LoginRequest();
		request.setEmail("baby@gmail.com");
		request.setPassword("plainpassword");

		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("plainpassword", "encodedpassword")).thenReturn(false);

		assertThrows(InvalidCredentialsException.class, () -> {
			authService.login(request);
		});

	}

	@Test
	public void changePasswordTest() throws UserNotFoundException, InvalidCredentialsException {

		User user = new User();
		user.setEmail("baby@gmail.com");
		user.setPassword("encodedoldPassword");
		ChangePasswordRequest change = new ChangePasswordRequest();
		change.setNewPassword("newPassword");
		change.setOldPassword("oldpassword");
		change.setEmail("baby@gmail.com");
		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("oldpassword", "encodedoldPassword")).thenReturn(true);
		when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPassword");
		String chnagedPassword = authService.changePassword(change);
		assertEquals("Password Changed Successfully!", chnagedPassword);

	}

	@Test
	public void changePasswordTest_Failed() throws UserNotFoundException, InvalidCredentialsException {
		User user = new User();
		user.setEmail("baby@gmail.com");
		user.setPassword("encodedoldPassword");
		ChangePasswordRequest change = new ChangePasswordRequest();
		change.setNewPassword("newPassword");
		change.setOldPassword("oldpassword");
		change.setEmail("baby@gmail.com");
		when(authRepository.findByEmail("baby@gmail.com")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("oldpassword", "encodedoldPassword")).thenReturn(false);

		assertThrows(InvalidCredentialsException.class, () -> {
			authService.changePassword(change);
		});

	}

}
