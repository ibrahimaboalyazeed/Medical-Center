package com.global.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.medical.entity.AppUser;


public interface UserRepo extends JpaRepository<AppUser, Long> {
	

	Optional<AppUser> findByEmail(String email);

}
