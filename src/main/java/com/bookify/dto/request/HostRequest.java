package com.bookify.dto.request;

import java.time.LocalDate;

import com.bookify.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HostRequest {

	@NotBlank(message = "Please provide a FirstName")
	@Size(min = 3, max = 25, message = "FirstName must be between 3 and 25 characters")
	private String firstName;

	@NotBlank(message = "Please provide a LastName")
	@Size(min = 3, max = 25, message = "FirstName must be between 3 and 25 characters")
	private String lastName;

	@Past(message = "Doğum tarihi geçmiş bir tarih olmalı.")
	@NotNull(message = "Doğum tarihi boş olamaz.")
	@Column(name = "birth_of_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthOfDate;

	@NotNull(message = "Gender boş olamaz.")
	private Gender gender;

	@NotNull(message = "Identity cannot be null.")
	@NotEmpty(message = "Identity cannot be empty.")
	@Size(min = 10, max = 10, message = "Identity must be exactly 10 symbols long.")
	private String identity;

	@NotNull(message = "Country cannot be null.")
	@NotEmpty(message = "Country cannot be empty.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Country must contain only letters and spaces.")
	@Size(min = 3, max = 25)
	private String country;

	@NotBlank(message = "Please provide a email address")
	@Email(message = "Please enter a valid email address")
	private String email;

	@NotBlank(message = "Phone number cannot be blank.")
	@Pattern(regexp = "^[0-9]+$", message = "Phone number must contain only digits.")
	@Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
	private String phone;

}
