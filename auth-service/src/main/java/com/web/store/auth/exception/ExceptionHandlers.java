package com.web.store.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ControllerAdvice
public class ExceptionHandlers {
	private static final Logger logger=LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity userNotFound(UserNotFoundException ex) {
		logger.error("UserNotFoundEception : {}",ex.getMessage());
		return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity invalidCredentials(UserNotFoundException ex) {
		logger.info("invalidCredentials : {}",ex.getMessage());
		return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
