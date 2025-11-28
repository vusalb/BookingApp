package com.bookify.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.bookify.entity.Amenity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PropertyRequest {

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Description is required")
	private String description;

	@NotNull(message = "Number of rooms is required")
	@Positive(message = "Number of rooms must be positive")
	private Integer numberOfRoom;

	@NotNull(message = "Price is required")
	private BigDecimal pricePerNight;

	@NotBlank(message = "Address is required")
	private String address;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "Country is required")
	private String country;

	@NotEmpty(message = "At least one amenity is required")
	private Set<Amenity> amenities;

	@NotEmpty(message = "Image URL is required")
	private List<String> imageUrl;

}
