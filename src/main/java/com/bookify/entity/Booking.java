package com.bookify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {

	@Column(name = "start_date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@Column(name = "end_date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;

	@Column(name = "total_price", nullable = false)
	private BigDecimal totalPrice;

	@OneToOne
	@JoinColumn(name = "property_id")
	private Property property;

}
