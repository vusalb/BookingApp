package com.bookify.dto.response;

import java.math.BigDecimal;
import java.util.Set;

import com.bookify.entity.Amenity;

import lombok.Data;

@Data
public class PropertyResponse {

	private String name;

	private String description;

	private String address;

	private String city;

	private String country;

	private Integer numberOfRoom;

	private BigDecimal pricePerNight;

	private Set<Amenity> amenities;

}
