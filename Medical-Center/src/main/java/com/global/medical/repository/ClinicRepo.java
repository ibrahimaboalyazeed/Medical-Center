package com.global.medical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.global.medical.entity.Clinic;


public interface ClinicRepo extends JpaRepository<Clinic, Long>{

	Optional<Clinic> findByName(String name);

	List<Clinic> findByNameContaining(String name);
	
	 Clinic findByAppUserId(long id);
	 
    @Query(value = "SELECT  `user_id` FROM `clinics` WHERE user_id = :id", nativeQuery = true)
	Object findClinicUserId(Long id);

	//long findAppUserId(Long id);

    Clinic findExaminationPriceById(Long id);

}
