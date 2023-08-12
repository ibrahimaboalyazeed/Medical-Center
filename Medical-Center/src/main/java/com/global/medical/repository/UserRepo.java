package com.global.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.medical.entity.AppUser;


@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
	

	Optional<AppUser> findByEmail(String email);

}
