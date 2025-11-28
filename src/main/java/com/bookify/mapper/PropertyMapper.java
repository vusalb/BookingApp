package com.bookify.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.bookify.dto.request.PropertyRequest;
import com.bookify.dto.response.PropertyResponse;
import com.bookify.entity.Property;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PropertyMapper {

	PropertyResponse entityToDto(Property property);

	Property dtoToEntity(PropertyRequest request);

	List<PropertyResponse> entityToDtoList(List<Property> properties);

	List<Property> dtoToEntityList(List<PropertyRequest> requests);

	void updatePropertyFromDto(@MappingTarget Property property, PropertyRequest request);

}
