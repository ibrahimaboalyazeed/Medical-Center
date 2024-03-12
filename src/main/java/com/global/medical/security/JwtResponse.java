package com.global.medical.security;

import java.util.Set;

import com.global.medical.entity.Role;


public class JwtResponse {

	private String token;
	private String email;
	private String username;
	private Set<Role> roles;
	private String status;


	
	

	public JwtResponse(String token, String email, String username, Set<Role> roles, String status) {
		super();
		this.token = token;
		this.email = email;
		this.username = username;
		this.roles = roles;
		this.status = status;
	}
	
	public JwtResponse(String token, String email, Set<Role> roles, String status) {
		super();
		this.token = token;
		this.email = email;
		this.roles = roles;
		this.status = status;
	}

	public JwtResponse() {

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

}