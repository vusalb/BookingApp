package com.bookify.service;

import com.bookify.auth.AuthResponse;
import com.bookify.auth.AuthUserLogin;
import com.bookify.auth.AuthUserRegister;
import com.bookify.auth.RefreshTokenRequest;

public interface AuthenticateService {

	public String register(AuthUserRegister register);

	public AuthResponse login(AuthUserLogin login);

	public AuthResponse refreshToken(RefreshTokenRequest request);

}
