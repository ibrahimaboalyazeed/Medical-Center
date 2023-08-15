package com.global.medical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.MedicalSpecialty;

@Repository
public interface MedicalSpecialtyRepo extends JpaRepository<MedicalSpecialty, Long>{

	Optional<MedicalSpecialty> findByName(String name);

	List<MedicalSpecialty> findByNameContaining(String name);
	

}
