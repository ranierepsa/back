package br.com.pitang.back.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.pitang.back.user.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests( (authorize) -> authorize
	    		.requestMatchers("/api/cars/**", "/api/me/**").authenticated()
	    	    .anyRequest().permitAll()
	     )
	    .csrf().disable()
	    .addFilterBefore(new JwtRequestFilter(userService), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}
