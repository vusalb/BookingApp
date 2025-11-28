package com.bookify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookify.jwt.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationProvider authenticationProvider;
	private final JwtFilter jwtFilter;

	public static final String REGISTER = "/api/auth/register";
	public static final String LOGIN = "/api/auth/login";
	public static final String REFRESH_TOKEN = "/api/auth/refresh-token";
	public static final String[] SWAGGER = { "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html" };
	public static final String CONSOLE = "/h2-console/**";

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
				.authorizeHttpRequests(auth -> auth.requestMatchers(LOGIN, REGISTER, REFRESH_TOKEN).permitAll()
						.requestMatchers(SWAGGER).permitAll().requestMatchers(CONSOLE).permitAll()
						.requestMatchers("/api/host/**").hasAnyRole("USER", "ADMIN").requestMatchers("/api/property/**")
						.hasAnyRole("USER", "ADMIN").anyRequest().authenticated())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
}
