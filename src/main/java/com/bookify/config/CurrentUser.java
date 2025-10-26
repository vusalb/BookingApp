package com.bookify.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bookify.entity.User;
import com.bookify.exception.MyApplicationException;
import com.bookify.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrentUser {

	private final UserRepository userRepository;

	public User getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			throw new MyApplicationException("No authenticated user found");
		}

		Object principal = authentication.getPrincipal();

		if (principal instanceof User user) {
			return user;
		}

		if (principal instanceof UserDetails userDetails) {
			String email = userDetails.getUsername();
			return findUserByEmail(email);
		}

		String email = principal.toString();
		return findUserByEmail(email);
	}

	private User findUserByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new MyApplicationException("User not found with email: " + email));
	}
}
