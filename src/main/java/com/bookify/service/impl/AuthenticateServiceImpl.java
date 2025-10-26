package com.bookify.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookify.auth.AuthResponse;
import com.bookify.auth.AuthUserLogin;
import com.bookify.auth.AuthUserRegister;
import com.bookify.auth.RefreshTokenRequest;
import com.bookify.config.CustomUserDetails;
import com.bookify.entity.Role;
import com.bookify.entity.RoleName;
import com.bookify.entity.User;
import com.bookify.exception.AlreadyExistException;
import com.bookify.exception.MyApplicationException;
import com.bookify.exception.ResourceNotFoundException;
import com.bookify.jwt.JwtService;
import com.bookify.repository.RoleRepository;
import com.bookify.repository.UserRepository;
import com.bookify.service.AuthenticateService;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final JwtService jwtService;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	@Override
	public String register(AuthUserRegister register) {

		if (userRepository.existsByEmail(register.getEmail())) {
			LOGGER.warn("Attempt to register with an existing email: {}", register.getEmail());
			throw new AlreadyExistException("Email is already in use.");
		}

		Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
				.orElseGet(() -> roleRepository.save(new Role(null, RoleName.ROLE_USER)));

		User user = User.builder().email(register.getEmail()).password(passwordEncoder.encode(register.getPassword()))
				.roles(Set.of(userRole)).build();
		userRepository.save(user);

		return "User registered successfully.";
	}

	@Override
	public AuthResponse login(AuthUserLogin login) {

		try {

			LOGGER.info("Attempting to authenticate user: {}", login.getEmail());

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					login.getEmail(), login.getPassword());
			authenticationManager.authenticate(authentication);

			User user = userRepository.findByEmail(login.getEmail()).orElseThrow(() -> {

				LOGGER.error("User not found: {}", login.getEmail());
				return new ResourceNotFoundException("User not found");
			});

			CustomUserDetails userDetails = new CustomUserDetails(user);
			String accessToken = jwtService.generateAccessToken(userDetails);
			String refreshToken = jwtService.generateRefreshToken(userDetails);

			LOGGER.info("User {} logged in successfully.", login.getEmail());
			return new AuthResponse(accessToken, refreshToken);

		} catch (UsernameNotFoundException ex) {
			LOGGER.error("Username not found: {}", login.getEmail(), ex);
			throw new ResourceNotFoundException("User not found");

		} catch (BadCredentialsException ex) {
			LOGGER.error("Invalid credentials for user: {}", login.getEmail(), ex);
			throw new IllegalArgumentException("Invalid credentials");

		} catch (Exception ex) {
			LOGGER.error("An error occurred during login for user: {}", login.getEmail(), ex);
			throw new MyApplicationException("An error occurred during login");
		}

	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {

		try {

			LOGGER.info("Refreshing token for user with refresh token: {}", request.getRefreshToken());

			String refreshTokenRequest = request.getRefreshToken();
			String email = jwtService.extractEmail(refreshTokenRequest);

			if (email == null || jwtService.isTokenExpired(refreshTokenRequest)) {
				LOGGER.error("Invalid or expired refresh token: {}", refreshTokenRequest);
				throw new MyApplicationException("Invalid or expired refresh token");
			}

			User user = userRepository.findByEmail(email).orElseThrow(() -> {

				LOGGER.error("User not found: {}", email);
				return new ResourceNotFoundException("User not found");
			});

			CustomUserDetails userDetails = new CustomUserDetails(user);
			String accessToken = jwtService.generateAccessToken(userDetails);
			String refreshToken = jwtService.generateRefreshToken(userDetails);

			LOGGER.info("Successfully refreshed token for user: {}", email);
			return new AuthResponse(accessToken, refreshToken);

		} catch (ExpiredJwtException ex) {
			LOGGER.error("Refresh token has expired.", ex);
			throw new MyApplicationException("Refresh token has expired");

		} catch (Exception ex) {
			LOGGER.error("An error occurred while refreshing the access token", ex);
			throw new MyApplicationException("An error occurred while refreshing the access token");
		}
	}

}
