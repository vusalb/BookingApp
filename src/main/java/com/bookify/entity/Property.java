package com.bookify.entity;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "number_of_room", nullable = false)
	private Integer numberOfRoom;

	@Column(name = "price_per_night", nullable = false)
	private BigDecimal pricePerNight;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "image", nullable = false)
	private String imageURL;

	@ElementCollection
	@CollectionTable(name = "property_amenities", joinColumns = @JoinColumn(name = "property_id"))
	@Column(name = "amenity")
	@Enumerated(EnumType.STRING)
	private Set<Amenity> amenities;

	@ManyToOne
	@JoinColumn(name = "host_id")
	private Host host;

	@OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
	private Booking booking;

}
