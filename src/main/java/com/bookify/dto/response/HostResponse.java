package com.bookify.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class HostResponse {

	private String firstName;

	private String lastName;

	private String email;

	private LocalDate birthOfDate;

	private String country;

	private String phone;

}
