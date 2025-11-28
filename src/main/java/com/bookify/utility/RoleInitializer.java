package com.bookify.utility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bookify.entity.Role;
import com.bookify.entity.RoleName;
import com.bookify.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

	private final RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		if (!roleRepository.existsByRoleName(RoleName.USER)) {
			roleRepository.save(Role.builder().roleName(RoleName.USER).build());
		}

		if (!roleRepository.existsByRoleName(RoleName.ADMIN)) {
			roleRepository.save(Role.builder().roleName(RoleName.ADMIN).build());
		}

	}

}
