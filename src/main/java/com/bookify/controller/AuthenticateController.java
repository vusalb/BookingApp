package com.bookify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookify.auth.AuthResponse;
import com.bookify.auth.AuthUserLogin;
import com.bookify.auth.AuthUserRegister;
import com.bookify.auth.RefreshTokenRequest;
import com.bookify.service.AuthenticateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticateController {

	private final AuthenticateService authenticateService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody AuthUserRegister register) {
		authenticateService.register(register);

		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");

	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthUserLogin login) {
		AuthResponse response = authenticateService.login(login);

		return ResponseEntity.ok(response);
	}

	@PostMapping("refresh-token")
	public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
		AuthResponse response = authenticateService.refreshToken(request);

		return ResponseEntity.ok(response);

	}
}
