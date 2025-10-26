package com.bookify.dto.request;

import java.math.BigDecimal;
import java.util.Set;

import com.bookify.entity.Amenity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertyRequest {

	@NotBlank(message = "Property name cannot be blank.")
	@Size(min = 3, max = 100, message = "Property name must be between 3 and 100 characters.")
	private String name;

	@Size(max = 500, message = "Description cannot exceed 500 characters.")
	private String description;

	@NotBlank(message = "Address cannot be blank.")
	@Size(min = 5, max = 150, message = "Address must be between 5 and 150 characters.")
	private String address;

	@NotBlank(message = "City cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "City must contain only letters and spaces.")
	@Size(min = 2, max = 50, message = "City must be between 2 and 50 characters.")
	private String city;

	@NotBlank(message = "Country cannot be blank.")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Country must contain only letters and spaces.")
	@Size(min = 3, max = 50, message = "Country must be between 3 and 50 characters.")
	private String country;

	@NotNull(message = "Number of rooms must be provided.")
	@Min(value = 1, message = "Property must have at least 1 room.")
	@Max(value = 1000, message = "Property cannot have more than 1000 rooms.")
	private Integer numberOfRoom;

	@NotNull(message = "Price per night must be provided.")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
	private BigDecimal pricePerNight;

	@NotBlank(message = "Image URL cannot be blank.")
	@Size(max = 255, message = "Image URL is too long.")
	private String imageURL;

	@NotEmpty(message = "At least one amenity must be selected.")
	private Set<Amenity> amenities;

}
