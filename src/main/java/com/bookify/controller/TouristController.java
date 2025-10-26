//package com.bookify.controller;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bookify.dto.request.TouristRequest;
//import com.bookify.dto.response.TouristResponse;
//import com.bookify.dto.response.TouristResponses;
//import com.bookify.service.TouristService;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/tourist")
//@RequiredArgsConstructor
//public class TouristController {
//
//	private final TouristService touristService;
//
//	@PostMapping
//	public ResponseEntity<String> createTourist(@Valid @RequestBody TouristRequest touristRequest) {
//
//		touristService.createTourist(touristRequest);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body("Tourist successfully created");
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<TouristResponse> findTouristById(@PathVariable Long id) {
//
//		TouristResponse dtoTourist = touristService.findTouristById(id);
//
//		return ResponseEntity.status(HttpStatus.OK).body(dtoTourist);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<String> updateTouristById(@PathVariable Long id,
//			@Valid @RequestBody TouristRequest dtoTouristIU) {
//
//		touristService.updateTouristById(id, dtoTouristIU);
//
//		return ResponseEntity.status(HttpStatus.OK).body("Tourist successfully updated");
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<String> updateTouristById(@PathVariable Long id) {
//
//		touristService.deleteTouristById(id);
//
//		return ResponseEntity.status(HttpStatus.OK).body("Tourist successfully deleted");
//	}
//
//	@GetMapping("/search")
//	public ResponseEntity<TouristResponse> findTouristByIdentityOrEmail(
//			@RequestParam(name = "identity", required = false) String identity,
//			@RequestParam(name = "email", required = false) String email) {
//
//		TouristResponse touristByIdentityOrEmail = touristService.findTouristByIdentityOrEmail(identity, email);
//
//		return ResponseEntity.status(HttpStatus.OK).body(touristByIdentityOrEmail);
//	}
//
//	@GetMapping("/tourists")
//	public ResponseEntity<TouristResponses> getAllTourists(@RequestParam(defaultValue = "0") int pageNumber,
//			@RequestParam(defaultValue = "25") int pageSize) {
//
//		TouristResponses responses = touristService.findAllTourists(pageNumber, pageSize);
//
//		return ResponseEntity.status(HttpStatus.OK).body(responses);
//	}
//
//}
