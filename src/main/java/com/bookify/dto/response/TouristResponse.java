package com.bookify.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TouristResponse {

	private String firstName;

	private String lastName;

	private String gender;

	private LocalDate birthOfDate;

	private String country;

	private String email;

	private String phone;

}
