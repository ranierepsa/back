package br.com.pitang.back.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.pitang.back.exception.ElementNotFoundException;
import br.com.pitang.back.exception.ExpiredTokenException;
import br.com.pitang.back.exception.InvalidCredentialsException;
import br.com.pitang.back.exception.InvalidFieldsException;
import br.com.pitang.back.exception.MissingFieldsException;
import br.com.pitang.back.exception.TokenNotSentException;
import br.com.pitang.back.exception.UniqueLicensePlateException;
import br.com.pitang.back.exception.UniqueUserEmailException;
import br.com.pitang.back.exception.UniqueUserLoginException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {UniqueLicensePlateException.class})
	ResponseEntity<HttpErroResponse> handleUniqueLicensePlate(HttpServletRequest request, UniqueLicensePlateException ex) {
		HttpErroResponse response = new HttpErroResponse("License plate already exists", HttpStatus.CONFLICT.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {UniqueUserEmailException.class})
	ResponseEntity<HttpErroResponse> handleUniqueUserEmail(HttpServletRequest request, UniqueUserEmailException ex) {
		HttpErroResponse response = new HttpErroResponse("Email already exists", HttpStatus.CONFLICT.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = {UniqueUserLoginException.class})
	ResponseEntity<HttpErroResponse> handleUniqueUserLogin(HttpServletRequest request, UniqueUserLoginException ex) {
		HttpErroResponse response = new HttpErroResponse("Login already exists", HttpStatus.CONFLICT.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = {ElementNotFoundException.class})
	ResponseEntity<HttpErroResponse> handleElementNotFound(HttpServletRequest request, ElementNotFoundException ex) {
		HttpErroResponse response = new HttpErroResponse("Element requested not found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {MissingFieldsException.class})
	ResponseEntity<HttpErroResponse> handleMissingFields(HttpServletRequest request, MissingFieldsException ex) {
		HttpErroResponse response = new HttpErroResponse("Missing fields", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {InvalidFieldsException.class})
	ResponseEntity<HttpErroResponse> handleInvalidFields(HttpServletRequest request, InvalidFieldsException ex) {
		HttpErroResponse response = new HttpErroResponse("Invalid fields", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {InvalidCredentialsException.class})
	ResponseEntity<HttpErroResponse> handleInvalidCredentials(HttpServletRequest request, InvalidCredentialsException ex) {
		HttpErroResponse response = new HttpErroResponse("Invalid login or password", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {TokenNotSentException.class})
	ResponseEntity<HttpErroResponse> handleTokenNotSent(HttpServletRequest request, TokenNotSentException ex) {
		HttpErroResponse response = new HttpErroResponse("Unauthorized", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value = {ExpiredTokenException.class})
	ResponseEntity<HttpErroResponse> handleExpiredToken(HttpServletRequest request, ExpiredTokenException ex) {
		HttpErroResponse response = new HttpErroResponse("Unauthorized - invalid session", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.UNAUTHORIZED);
	}
}