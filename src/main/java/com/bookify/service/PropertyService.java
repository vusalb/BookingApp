package com.bookify.service;

import com.bookify.dto.request.PropertyRequest;
import com.bookify.dto.response.PropertyResponse;
import com.bookify.dto.response.PropertyResponses;

public interface PropertyService {

	public String createProperty(PropertyRequest request);

	public PropertyResponse getPropertyById(Long id);

	public String updatePropertyById(Long id, PropertyRequest request);

	public String deletePropertyById(Long id);

	public PropertyResponses findPropertiesByCity(String city);

	public PropertyResponses findAllProperties();

}
