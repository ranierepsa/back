package br.com.pitang.back.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import br.com.pitang.back.user.UserService;
import jakarta.servlet.http.HttpServletRequest;

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
	    .addFilterBefore(new JwtRequestFilter(userService), UsernamePasswordAuthenticationFilter.class)
	    .cors(conf -> {
	    	conf.configurationSource(new CorsConfigurationSource() {	
				@Override
				public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					CorsConfiguration config = new CorsConfiguration();
			        config.setAllowedOrigins(List.of("*"));
			        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			        config.setAllowedHeaders(List.of("Content-Type", "User-Agent", "Authorization"));
			        return config;
				}
			});
	    });

	    return http.build();
	}
}
