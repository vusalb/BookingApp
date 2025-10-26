package com.bookify.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class BookingResponse {

	private Long id;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private Date startDate;

	private Date endDate;

	private BigDecimal totalPrice;

}
