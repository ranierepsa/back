package br.com.pitang.back.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pitang.back.exception.InvalidCredentialsException;
import br.com.pitang.back.security.SecurityService;
import br.com.pitang.back.security.TokenService;
import br.com.pitang.back.user.User;
import br.com.pitang.back.user.UserService;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/signin")
	public ResponseEntity<?> signIn(@RequestBody UserCredentialDTO userCredentials) {
		User user = userService.findByLogin(userCredentials.getLogin());
		
		if (user == null || !SecurityService.getInstance().verifyPasswords(userCredentials.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException();
		}
		
		user.setLastLogin(LocalDateTime.now());
		user = userService.update(user.getId(), user);
		return new ResponseEntity<>(TokenService.createTokenUser(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/me")
	public ResponseEntity<User> getLoggedUserInfo() {
		User user = SecurityService.getInstance().getLoggedUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}