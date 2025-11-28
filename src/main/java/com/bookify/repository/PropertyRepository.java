package com.bookify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookify.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

	@Query("SELECT p FROM Property p WHERE LOWER(p.city) LIKE LOWER(CONCAT('%', :city, '%'))")
	List<Property> findPropertiesByCity(@Param("city") String city);

}
