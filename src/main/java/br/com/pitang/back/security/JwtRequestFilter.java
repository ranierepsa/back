package br.com.pitang.back.security;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.pitang.back.exception.ExpiredTokenException;
import br.com.pitang.back.exception.TokenNotSentException;
import br.com.pitang.back.user.User;
import br.com.pitang.back.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtRequestFilter extends OncePerRequestFilter {
	
	static final String HEADER_STRING = "Authorization";
	static final String TOKEN_PREFIX = "Bearer";
	static final String SECRET = "5NM3lFuLfUHYZ8YaoxiujkhSUI3yf2WK";

	private UserService userService;

	public JwtRequestFilter(UserService userService) {
		this.userService = userService;
	}
	
	private UUID getUserId(String token) {
		DecodedJWT jwt = JWT.decode(token);
		if (jwt == null)
			return null;

		return UUID.fromString(jwt.getClaim("id").asString());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String requestPath = request.getRequestURI();
		if (requestPath == null) {
			requestPath = "";
		}
		
		if (requestPath.contains("api/signin")) {
			SecurityContextHolder.getContext().setAuthentication(null);
			request.getRequestDispatcher(request.getServletPath()).forward(request, response);
			return;
		}

		if (request.getMethod().equalsIgnoreCase("OPTIONS") || requestPath.contains("api/users")) {
			request.getRequestDispatcher(request.getServletPath()).forward(request, response);
			return;
		}

		String token = request.getHeader(HEADER_STRING);
		if (token == null) {
			throw new TokenNotSentException();
		}
		
		DecodedJWT jwt = JWT.decode(token.replace(TOKEN_PREFIX, ""));
		if (System.currentTimeMillis() > jwt.getExpiresAt().getTime())
			throw new ExpiredTokenException();

		UUID userId = getUserId(token.replace(TOKEN_PREFIX, ""));
		User user = this.userService.findById(userId);
		request.getSession().setAttribute("user", user);
		SecurityService.getInstance().setSession(request.getSession());

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user.getLogin(), null, Collections.emptyList());
		SecurityContextHolder.getContext().setAuthentication(auth);
		filterChain.doFilter(request, response);
	}
}
