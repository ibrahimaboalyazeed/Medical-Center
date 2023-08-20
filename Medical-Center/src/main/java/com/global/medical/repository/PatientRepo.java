package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{

	Patient findByAppUserId(long id);
	

	
	
	

}
