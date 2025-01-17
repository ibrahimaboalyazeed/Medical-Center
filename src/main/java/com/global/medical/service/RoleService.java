package com.global.medical.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.medical.entity.Role;
import com.global.medical.repository.RoleRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepo roleRepo;

	public List<Role> findAll() {

		return roleRepo.findAll();
	}

	public Role findById(Long id) {

		return roleRepo.findById(id).orElse(null);
	}
	
	public Role findByName(String name) {

		return roleRepo.findByName(name);
	}
	
	public Role save(Role entity) {

		return roleRepo.save(entity);
	}

	public List<Role> insertAll(List<Role> roles) {

		return roleRepo.saveAll(roles);
	}
	
	public Role getUserRole() {
		
		return roleRepo.findRoleByName("USER_ROLE");
	}
	public Role getAdminRole() {
		
		return roleRepo.findRoleByName("ADMIN_ROLE");
	}
}
