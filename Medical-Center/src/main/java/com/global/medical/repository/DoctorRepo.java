package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long>{

}
