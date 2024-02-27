package br.com.pitang.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests( (authorize) -> authorize
	    	   .anyRequest().permitAll()
//	           .requestMatchers("/").permitAll()
//	           .requestMatchers("/user/cadastro").hasAuthority(ADMIN)
//	           .anyRequest().authenticated()
	     ).csrf().disable();

	    return http.build();
	}
}
