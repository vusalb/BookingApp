//package com.bookify.service.impl;
//
//import java.util.List;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.bookify.config.CurrentUser;
//import com.bookify.dto.request.TouristRequest;
//import com.bookify.dto.response.TouristResponse;
//import com.bookify.dto.response.TouristResponses;
//import com.bookify.entity.Tourist;
//import com.bookify.entity.User;
//import com.bookify.exception.ResourceNotFoundException;
//import com.bookify.mapper.TouristMapper;
//import com.bookify.service.TouristService;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class TouristServiceImpl implements TouristService {
//
//	private final TouristMapper mapper;
//	private final CurrentUser currentUser;
//
//	@Override
//	public String createTourist(TouristRequest touristRequest) {
//
//		Tourist tourist = mapper.dtoToEntity(touristRequest);
//
//		Tourist savedTourist = touristRepository.save(tourist);
//
//		return "Tourist: " + savedTourist.getFirstName() + " successfully created with ID: " + savedTourist.getId();
//	}
//
//	@Override
//	public TouristResponse findTouristById(Long id) {
//
//		User user = currentUser.getCurrentUser();
//		Long userId = user.getId();
//
//		Tourist tourist = touristRepository.findTouristById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("Tourist not found with ID: " + userId));
//
//		TouristResponse entityToDto = mapper.entityToDto(tourist);
//
//		return entityToDto;
//	}
//
//	@Override
//	public String updateTouristById(Long id, TouristRequest dtoTouristIU) {
//
//		User user = currentUser.getCurrentUser();
//		long userId = user.getId();
//
//		Tourist tourist = touristRepository.findTouristById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("Tourist not found with ID: " + userId));
//
//		mapper.updateTouristFromDto(dtoTouristIU, tourist);
//
//		touristRepository.save(tourist);
//
//		return "Tourist successfully updated with ID: " + userId;
//	}
//
//	@Override
//	public String deleteTouristById(Long id) {
//
//		User user = currentUser.getCurrentUser();
//		Long userId = user.getId();
//
//		Tourist tourist = touristRepository.findTouristById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("Tourist not found with ID: " + userId));
//
//		touristRepository.deleteById(tourist.getId());
//
//		return "Tourist successfully deleted with ID: " + tourist.getId();
//	}
//
//	@Override
//	public TouristResponse findTouristByIdentityOrEmail(String identity, String email) {
//
//		Tourist tourist = touristRepository.findTouristByIdentityOrEmail(identity, email)
//				.orElseThrow(() -> new ResourceNotFoundException(
//						"Tourist not found with identity or email: " + identity + " or " + email));
//
//		TouristResponse entityToDto = mapper.entityToDto(tourist);
//
//		return entityToDto;
//	}
//
//	@Override
//	public TouristResponses findAllTourists(int pageNumber, int pageSize) {
//
//		Pageable pageable = PageRequest.of(pageNumber, pageSize);
//
//		Page<Tourist> page = touristRepository.findAll(pageable);
//
//		List<TouristResponse> touristResponse = page.stream().map(mapper::entityToDto).toList();
//
//		return new TouristResponses(touristResponse);
//	}
//
//}
