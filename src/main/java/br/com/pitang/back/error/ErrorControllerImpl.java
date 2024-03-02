package br.com.pitang.back.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ErrorControllerImpl implements ErrorController{
	
	@RequestMapping("/error")
	public ResponseEntity<HttpErroResponse> handleError(HttpServletRequest request) throws Throwable {
		HttpErroResponse response = new HttpErroResponse("Unauthorized", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<HttpErroResponse>(response, HttpStatus.UNAUTHORIZED);
	}
}
