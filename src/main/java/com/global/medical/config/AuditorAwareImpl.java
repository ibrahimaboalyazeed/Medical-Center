package com.global.medical.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String>{
	
	@Override
	public Optional<String> getCurrentAuditor() {
		
		// should get userName from spring security
		
		return Optional.of("test user");
	}

}
