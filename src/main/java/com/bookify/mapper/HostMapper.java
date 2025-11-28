package com.bookify.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.bookify.dto.request.HostRequest;
import com.bookify.dto.response.HostResponse;
import com.bookify.entity.Host;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HostMapper {

	HostResponse entityToDto(Host host);

	Host dtoToEntity(HostRequest request);

	List<HostResponse> entityToDtoList(List<Host> hosts);

	List<Host> dtoToEntityList(List<HostRequest> requests);

	void updateHostFromDto(@MappingTarget Host host, HostRequest request);

}
