package com.bookify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookify.entity.Role;
import com.bookify.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(RoleName roleName);

	boolean existsByRoleName(RoleName roleName);

}
