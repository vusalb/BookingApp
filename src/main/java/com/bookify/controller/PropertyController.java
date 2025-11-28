package com.bookify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookify.dto.request.PropertyRequest;
import com.bookify.dto.response.PropertyResponse;
import com.bookify.dto.response.PropertyResponses;
import com.bookify.service.PropertyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/property")
@RequiredArgsConstructor
public class PropertyController {

	private final PropertyService propertyService;

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> createProperty(@Valid @RequestBody PropertyRequest request) {

		propertyService.createProperty(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Property created successfully");

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id) {

		PropertyResponse response = propertyService.getPropertyById(id);

		return ResponseEntity.ok(response);

	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> updatePropertyById(@PathVariable Long id,
			@Valid @RequestBody PropertyRequest request) {

		propertyService.updatePropertyById(id, request);

		return ResponseEntity.ok("Property updated successfully");

	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> deletePropertyById(@PathVariable Long id) {

		propertyService.deletePropertyById(id);

		return ResponseEntity.ok("Property deleted successfully");

	}

	@GetMapping("/city")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<PropertyResponses> findPropertiesByCity(@RequestParam String city) {

		PropertyResponses responses = propertyService.findPropertiesByCity(city);

		return ResponseEntity.ok(responses);

	}

	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<PropertyResponses> findAllProperties() {

		PropertyResponses responses = propertyService.findAllProperties();

		return ResponseEntity.ok(responses);
	}

}
