package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{
	
	
	 @Query(value = "SELECT  `user_id` FROM `doctors` WHERE user_id = :id", nativeQuery = true)
	 Object findDoctorUserId(Long id);

}
