package com.global.medical.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.medical.entity.AppUser;
import com.global.medical.entity.Role;
import com.global.medical.error.CustomException;
import com.global.medical.repository.UserRepo;
import com.global.medical.security.AppUserDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo userRepo;
	
	private  final RoleService roleService;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public AppUser findByEmail(String email) {
		
		return userRepo.findByEmail(email).orElseThrow(() -> new CustomException("User is not found"));
	}
	
	
	public List<AppUser> findAll (){
		
		return userRepo.findAll();
	}
	
    public AppUser findById (Long id){
		
		return userRepo.findById(id).orElseThrow((() -> new CustomException("User is not found")));
	}
    
    public AppUser save(AppUser entity) {
    	
    	if(!userRepo.findByEmail(entity.getEmail()).isEmpty())
    	{
    		throw new CustomException("This Email is already exists");
    	}
    	AppUser appUser =new AppUser();
    	appUser.setEmail(entity.getEmail());
    	appUser.setPassword(passwordEncoder.encode(entity.getPassword()));
    	 // Get the customer role entity by name
	    Role userRole = roleService.findByName("ROLE_USER");
	    // Make sure the role exists
	    if (userRole == null) {
	        throw new RuntimeException("ROLE_USER not found in database!");
	    }
	    // Add the role to the customer's set of roles
	    Set<Role> roles = new HashSet<>();
	    roles.add(userRole);
	    appUser.setRoles(roles);
		return userRepo.save(appUser);
	}
	public List<AppUser> saveAll (List<AppUser> users){
		
		return userRepo.saveAll(users);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	  Optional<AppUser> appUser =	userRepo.findByEmail(username);
	  
	  if (!appUser.isPresent()) {
		  
		  throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
	  }
		
		return new AppUserDetail(appUser.get());
	}


	public Optional<AppUser> findUserById(Long id) {
		
		return userRepo.findById(id);
	}


	
//	private static List<GrantedAuthority> getAuthorities(AppUser user) {
//		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		 if(!user.getRoles().isEmpty()) {
//		        	user.getRoles().forEach(role -> {
//		        		authorities.add(new SimpleGrantedAuthority(role.getName()));	
//		        	});
//		        }
//		     return authorities;
//		}
}
