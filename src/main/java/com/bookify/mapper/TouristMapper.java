package com.bookify.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TouristMapper {

//	TouristResponse entityToDto(Tourist tourist);
//
//	Tourist dtoToEntity(TouristRequest touristRequest);
//
//	List<TouristResponse> entityToDtoList(List<Tourist> tourists);
//
//	List<Tourist> dtoToEntityList(List<TouristRequest> touristRequests);
//
//	@Mapping(target = "id", ignore = true)
//	void updateTouristFromDto(TouristRequest touristRequest, @MappingTarget Tourist tourist);

}
