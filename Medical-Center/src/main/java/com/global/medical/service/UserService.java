package com.global.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.global.medical.entity.AppUser;
import com.global.medical.error.CustomException;
import com.global.medical.repository.UserRepo;
import com.global.medical.security.AppUserDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepo userRepo;
	
	private final PasswordEncoder passwordEncoder;
	
	
	public AppUser findByEmail(String email) {
		
		return userRepo.findByEmail(email).orElseThrow(() -> new CustomException("User is not found"));
	}
	
	
	public List<AppUser> findAll (){
		
		return userRepo.findAll();
	}
	
    public AppUser findById (Long id){
		
		return userRepo.findById(id).orElse(null);
	}
    
    public AppUser save(AppUser entity) {
    	AppUser appUser =new AppUser();
    	appUser.setEmail(entity.getEmail());
    	appUser.setPassword(passwordEncoder.encode(entity.getPassword()));
		return userRepo.save(appUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	  Optional<AppUser> appUser =	userRepo.findByEmail(username);
	  
	  if (!appUser.isPresent()) {
		  
		  throw new UsernameNotFoundException("This User Not found with selected user name :- " + username);
	  }
		
		return new AppUserDetail(appUser.get());
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
