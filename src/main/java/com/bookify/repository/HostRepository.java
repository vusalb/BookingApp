package com.bookify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookify.entity.Host;

public interface HostRepository extends JpaRepository<Host, Long> {

	@Query("SELECT h FROM Host h where h.email = :email")
	Optional<Host> findHostByEmail(@Param("email") String email);

	@Query("SELECT h FROM Host h where h.user.id = :userId")
	Optional<Host> findByUserId(@Param("userId") Long userId);

}
