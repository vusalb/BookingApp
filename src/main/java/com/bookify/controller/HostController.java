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

import com.bookify.dto.request.HostRequest;
import com.bookify.dto.response.HostResponse;
import com.bookify.service.HostService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/host")
@RequiredArgsConstructor
public class HostController {

	private final HostService hostService;

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> createHost(@Valid @RequestBody HostRequest request) {

		hostService.createHost(request);

		return ResponseEntity.status(HttpStatus.CREATED).body("Host created successfully");

	}

	@GetMapping(path = "/{id}")
	@PreAuthorize("hasRole('ADMIN') or @currentUser.getCurrentUser().id == #id")
	public ResponseEntity<HostResponse> getHostById(@PathVariable Long id) {

		HostResponse response = hostService.getHostById(id);

		return ResponseEntity.ok(response);

	}

	@PutMapping(path = "/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> updateHostById(@PathVariable Long id, @Valid @RequestBody HostRequest request) {

		hostService.updateHostById(id, request);

		return ResponseEntity.ok("Host updated successfully");

	}

	@DeleteMapping(path = "/{id}")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> deleteHostById(@PathVariable Long id) {

		hostService.deleteHostById(id);

		return ResponseEntity.ok("Host deleted successfully");
	}

	@GetMapping(path = "/search")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<HostResponse> findHostByEmail(
			@RequestParam(name = "email", required = false) @Email String email) {

		HostResponse hostByEmail = hostService.findHostByEmail(email);

		return ResponseEntity.ok(hostByEmail);
	}

}
