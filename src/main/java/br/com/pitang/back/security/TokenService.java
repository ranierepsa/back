package br.com.pitang.back.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pitang.back.user.User;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

	private static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60;
//    private static final String SECRET = "WSiX+cY4jeIS864Ts/cmkcxgRyiDyWSDiIWN8f8d3EQ=";
	private static final String AUTHORITIES_KEY = "Authorization";

	public static String createToken(Map<String, Object> claims, String subject) {
		return Jwts
				.builder()
				.claims(claims)
				.subject(subject)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//				.signWith(SignatureAlgorithm.HS256, SECRET)
				.compact();
	}
	
	public static String createTokenUser(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", user.getId());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());
		claims.put("email", user.getEmail());
		claims.put("birthday", convertLocalDateToDate(user.getBirthday()));
		claims.put("login", user.getLogin());
		claims.put("password", user.getPassword());
		claims.put("phone", user.getPhone());
		claims.put("cars", List.of(user.getCars()));

		return createToken(claims, user.getLogin());
	}

	public String createTokenAuth(Authentication authentication) {

		String authorities = authentication.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));

		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime expirationDateTime = now.plus(JWT_TOKEN_VALIDITY, ChronoUnit.MILLIS);

		Date issueDate = Date.from(now.toInstant());
		Date expirationDate = Date.from(expirationDateTime.toInstant());

		return Jwts
				.builder()
				.subject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
//				.signWith(SignatureAlgorithm.HS256, SECRET)
				.issuedAt(issueDate)
				.expiration(expirationDate)
				.compact();
	}
	
	private static Date convertLocalDateToDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.of("America/Montreal")).toInstant());
	}
}
