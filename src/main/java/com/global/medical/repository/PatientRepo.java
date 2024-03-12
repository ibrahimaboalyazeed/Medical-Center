package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.medical.entity.Patient;

	
public interface PatientRepo extends JpaRepository<Patient, Long>{

	Patient findByAppUserId(long id);

    Patient findByFullNameAndPhoneNumberAndAge(String fullName,String phoneNumber,int age);
	

	
	
	

}
