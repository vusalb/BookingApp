package com.bookify.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserRegister {

	@NotBlank(message = "Please provide a email address")
	@Email(message = "Please enter a valid email address")
	private String email;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
	private String password;

}
