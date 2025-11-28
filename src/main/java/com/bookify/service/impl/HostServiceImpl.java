package com.bookify.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookify.config.CurrentUser;
import com.bookify.dto.request.HostRequest;
import com.bookify.dto.response.HostResponse;
import com.bookify.entity.Host;
import com.bookify.entity.User;
import com.bookify.exception.MyApplicationException;
import com.bookify.exception.ResourceNotFoundException;
import com.bookify.mapper.HostMapper;
import com.bookify.repository.HostRepository;
import com.bookify.repository.UserRepository;
import com.bookify.service.HostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HostServiceImpl implements HostService {

	private final HostRepository hostRepository;
	private final CurrentUser currentUser;
	private final HostMapper hostMapper;
	private final UserRepository userRepository;

	@Override
	public String createHost(HostRequest request) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Host host = hostMapper.dtoToEntity(request);
		host.setBirthOfDate(request.getBirthOfDate());

		host.setEmail(email);
		host.setUser(user);

		hostRepository.save(host);

		log.info("Host created successfully with ID: {}", host.getId());

		return "Host: " + " created successfully with ID: " + host.getId();

	}

	@Override
	public HostResponse getHostById(Long id) {

		long userId = currentUser.getCurrentUser().getId();

		log.debug("Fetching host with ID: {} for user ID: {}", id, userId);

		Host host = hostRepository.findById(id).orElseThrow(() -> {
			log.error("Host not found with ID: {}", id);
			return new ResourceNotFoundException("Host not found with ID: " + id);
		});

		if (!host.getUser().getId().equals(userId)) {
			log.error("User ID: {} is not allowed to access host ID: {}", userId, id);
			throw new MyApplicationException("You are not allowed to view this host");
		}

		HostResponse response = hostMapper.entityToDto(host);
		log.info("Host fetched successfully with ID: {}", id);

		return response;
	}

	@Override
	public String updateHostById(Long id, HostRequest request) {

		long userId = currentUser.getCurrentUser().getId();

		log.debug("Updating host with ID: {} for user ID: {}", id, userId);

		Host host = hostRepository.findById(id).orElseThrow(() -> {
			log.error("Host not found for update with ID: {}", id);
			return new ResourceNotFoundException("Host not found with ID: " + id);
		});

		if (!host.getUser().getId().equals(userId)) {
			log.error("User ID: {} is not allowed to update host ID: {}", userId, id);
			throw new MyApplicationException("You are not allowed to update this host");
		}

		hostMapper.updateHostFromDto(host, request);

		hostRepository.save(host);

		log.info("Host updated successfully with ID: {}", id);

		return "Host updated successfully with ID: " + id;
	}

	@Override
	public String deleteHostById(Long id) {

		Long userId = currentUser.getCurrentUser().getId();
		log.debug("Deleting host with ID: {} for user ID: {}", id, userId);

		Host host = hostRepository.findById(id).orElseThrow(() -> {
			log.error("Host not found for deletion with ID: {}", id);
			return new ResourceNotFoundException("Host not found with ID: " + id);
		});

		if (!host.getUser().getId().equals(userId)) {
			log.error("User ID: {} is not allowed to delete host ID: {}", userId, id);
			throw new MyApplicationException("You are not allowed to delete this host");
		}

		hostRepository.deleteById(id);
		log.info("Host deleted successfully with ID: {}", id);

		return "Host deleted successfully with ID: " + id;
	}

	@Override
	public HostResponse findHostByEmail(String email) {

		long userId = currentUser.getCurrentUser().getId();

		log.debug("Searching host with EMAIL: {} for user ID: {}", email, userId);

		Host host = hostRepository.findHostByEmail(email).orElseThrow(() -> {
			log.error("Host not found with EMAIL: {}", email);
			return new ResourceNotFoundException("Host not found with EMAIL: " + email);
		});

		if (!host.getUser().getId().equals(userId)) {
			log.error("User ID: {} is not allowed to access host with EMAIL: {}", userId, email);
			throw new MyApplicationException("You are not allowed to view this host");
		}

		HostResponse response = hostMapper.entityToDto(host);
		log.info("Host found with ID: {} for user ID: {}", host.getId(), userId);

		return response;
	}

}
