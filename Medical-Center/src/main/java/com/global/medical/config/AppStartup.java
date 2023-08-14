package com.global.medical.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.medical.entity.Role;
import com.global.medical.service.RoleService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AppStartup implements CommandLineRunner{
	
	private final RoleService roleService;


	@Override
	public void run(String... args) throws Exception {	
		
		
		if (roleService.findAll().isEmpty()) {
		Role role = new Role();
		role.setName("ADMIN_ROLE");

		Role role1 = new Role();
		role1.setName("USER_ROLE");

		roleService.insertAll(Arrays.asList(role, role1));

	}
		
		
	}

}
