package com.global.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.medical.entity.Role;


public interface RoleRepo extends JpaRepository<Role, Long> {
	
	Role findByName (String name);
	Role findRoleByName(String name);

}
