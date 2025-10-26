package com.bookify.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingRequest {

	@NotNull(message = "Start date cannot be null.")
	@FutureOrPresent(message = "Start date must be today or in the future.")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@NotNull(message = "End date cannot be null.")
	@Future(message = "End date must be in the future.")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@NotNull(message = "Total price cannot be null.")
	@DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero.")
	private BigDecimal totalPrice;

}
