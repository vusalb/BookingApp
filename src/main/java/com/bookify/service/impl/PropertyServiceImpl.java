package com.bookify.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookify.config.CurrentUser;
import com.bookify.dto.request.PropertyRequest;
import com.bookify.dto.response.PropertyResponse;
import com.bookify.dto.response.PropertyResponses;
import com.bookify.entity.Host;
import com.bookify.entity.Property;
import com.bookify.entity.User;
import com.bookify.exception.MyApplicationException;
import com.bookify.exception.ResourceNotFoundException;
import com.bookify.mapper.PropertyMapper;
import com.bookify.repository.HostRepository;
import com.bookify.repository.PropertyRepository;
import com.bookify.repository.UserRepository;
import com.bookify.service.PropertyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

	private final CurrentUser currentUser;
	private final PropertyRepository propertyRepository;
	private final PropertyMapper propertyMapper;
	private final UserRepository userRepository;
	private final HostRepository hostRepository;

	@Override
	public String createProperty(PropertyRequest request) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Host host = hostRepository.findByUserId(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Host profile not found"));

		Property property = propertyMapper.dtoToEntity(request);

		property.setHost(host);

		propertyRepository.save(property);

		return "Property created successfully";
	}

	@Override
	public PropertyResponse getPropertyById(Long id) {

		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found"));

		PropertyResponse response = propertyMapper.entityToDto(property);

		return response;
	}

	@Override
	public String updatePropertyById(Long id, PropertyRequest request) {

		long userID = currentUser.getCurrentUser().getId();

		Host host = hostRepository.findByUserId(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Host not found"));

		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found"));

		if (!property.getHost().getId().equals(host.getId())) {
			throw new MyApplicationException("You cannot update another host's property");
		}

		propertyMapper.updatePropertyFromDto(property, request);

		propertyRepository.save(property);

		return "Property updated successfully";
	}

	@Override
	public String deletePropertyById(Long id) {

		Long userID = currentUser.getCurrentUser().getId();

		Host host = hostRepository.findByUserId(userID)
				.orElseThrow(() -> new ResourceNotFoundException("Host not found"));

		Property property = propertyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Property not found"));

		if (!property.getHost().getId().equals(host.getId())) {
			throw new MyApplicationException("You cannot delete another host's property");
		}

		propertyRepository.deleteById(id);

		return "Property deleted successfully";
	}

	@Override
	public PropertyResponses findPropertiesByCity(String city) {

		List<Property> properties = propertyRepository.findPropertiesByCity(city);

		if (properties.isEmpty()) {
			throw new MyApplicationException("No properties found in this city");
		}

		List<PropertyResponse> entityToDtoList = propertyMapper.entityToDtoList(properties);

		PropertyResponses responses = new PropertyResponses();

		responses.setProperties(entityToDtoList);

		return responses;
	}

	@Override
	public PropertyResponses findAllProperties() {

		List<Property> allProperties = propertyRepository.findAll();

		List<PropertyResponse> entityToDtoList = propertyMapper.entityToDtoList(allProperties);

		PropertyResponses responses = new PropertyResponses();

		responses.setProperties(entityToDtoList);

		return responses;
	}

}
