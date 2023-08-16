package com.global.medical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Clinic;

@Repository
public interface ClinicRepo extends JpaRepository<Clinic, Long>{

	Optional<Clinic> findByName(String name);

	List<Clinic> findByNameContaining(String name);
	

}
