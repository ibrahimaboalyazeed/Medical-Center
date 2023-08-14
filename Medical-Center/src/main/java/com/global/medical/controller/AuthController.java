package com.global.medical.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.medical.entity.AppUser;
import com.global.medical.error.CustomException;
import com.global.medical.error.CustomResponse;
import com.global.medical.security.JwtResponse;
import com.global.medical.security.JwtTokenUtils;
import com.global.medical.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {


	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtils tokenUtiles;

	@PostMapping("/login")
	public Object login(@RequestBody Map<String, Object> body) {

		String email = (String) body.get("email");
		String password = (String) body.get("password");
		

		log.info("Authentication");
		  if (email == null || password == null) {
		        throw new CustomException("Email or password is missing");
		    }
		
		Authentication authentication = null;

		try {

			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		}
 
		catch (DisabledException dis) {
			throw new CustomException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new CustomException("The request is rejected because the credentials are invalid");
		}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//UserDetails userDetails = userService.loadUserByUsername(email);
			AppUser user = userService.findByEmail(email);
			String token = tokenUtiles.generateToken(email,false);

			return new CustomResponse(
					new JwtResponse(token, email, user.getEmail(), user.getRoles(), "Succeefully logged"));
		}

		return new CustomResponse("INVALID", HttpStatus.BAD_REQUEST.value());
	}


	@PostMapping("/signup")
	public Object signup(@RequestBody @Valid AppUser user) {

		AppUser userNew = userService.save(user);

		// Authenticate the user
		log.info("Authentication");
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		log.info("authentication >> " + authentication.isAuthenticated());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Generate JWT token
		String token = tokenUtiles.generateToken(user.getEmail(), true);

		return new CustomResponse(
				new JwtResponse(token, user.getEmail(), userNew.getRoles(), "Successfully signed up"));
	}

}
